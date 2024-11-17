package br.fecap.pi.fitsync;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class HomeFragment extends Fragment implements SensorEventListener {

    public static final String PREFS_NAME = "StepCountPrefs"; // Nome das preferências
    public static final String STEP_COUNT_KEY = "stepCount"; // Chave da contagem de passos
    public static final String INITIAL_STEP_COUNT_KEY = "initialStepCount"; // Chave para contagem inicial
    private static final int STEP_GOAL = 10000; // Meta de passos

    private ProgressBar circularProgressBar; // Barra de progresso circular
    private TextView stepCountTextView; // TextView para mostrar a contagem de passos
    private TextView distanciaText; // TextView para mostrar a distância em km
    private TextView kcalText; // TextView para mostrar as calorias queimadas
    private SensorManager sensorManager; // Gerenciador de sensores
    private Sensor stepCounterSensor; // Sensor do contador de passos
    private boolean isSensorAvailable; // Verifica se o sensor está disponível
    private int stepCount = 0; // Contagem atual de passos
    private int initialStepCount = 0; // Contagem inicial de passos do dia

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        circularProgressBar = view.findViewById(R.id.circularProgressBar);
        stepCountTextView = view.findViewById(R.id.stepCountTextView);
        distanciaText = view.findViewById(R.id.distanciaText); // Inicializa o TextView da distância
        kcalText = view.findViewById(R.id.kcalText); // Inicializa o TextView das calorias queimadas

        // Verifica e solicita permissões para contagem de passos
        checkPermissions();

        // Recupera a contagem de passos salva e a contagem inicial
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        stepCount = sharedPreferences.getInt(STEP_COUNT_KEY, 0);
        initialStepCount = sharedPreferences.getInt(INITIAL_STEP_COUNT_KEY, -1); // -1 indica que ainda não foi definido

        // Configura o SensorManager e o Step Counter
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isSensorAvailable = stepCounterSensor != null;
        }

        // Configura o AlarmManager para redefinir a contagem de passos à meia-noite
        setupMidnightResetAlarm();

        return view;
    }

    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACTIVITY_RECOGNITION}, 1);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isSensorAvailable) {
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_UI);
        }
        // Atualiza a UI com a contagem de passos
        updateUI();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isSensorAvailable) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            int currentStepCount = (int) event.values[0];

            // Define a contagem inicial ao abrir o app pela primeira vez
            if (initialStepCount == -1) {
                initialStepCount = currentStepCount;
                saveInitialStepCount(initialStepCount);
            }

            // Ajusta a contagem de passos para mostrar apenas os passos diários
            stepCount = currentStepCount - initialStepCount;

            if (stepCount > STEP_GOAL) {
                stepCount = STEP_GOAL;
            }

            // Salva e atualiza a UI com a contagem ajustada
            saveStepCount(stepCount);
            updateUI();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Não é necessário para o contador de passos
    }

    public void updateUI() {
        // Calcula o progresso como uma porcentagem da meta
        int progress = (int) ((stepCount / (float) STEP_GOAL) * 100);
        circularProgressBar.setProgress(progress); // Atualiza a barra de progresso
        stepCountTextView.setText(String.valueOf(stepCount)); // Atualiza o texto da contagem de passos

        // Calcula a distância em km (0,00078 km por passo)
        double distanciaEmKm = stepCount * 0.00078;
        distanciaText.setText(String.format("%.2f km", distanciaEmKm)); // Atualiza o TextView da distância

        // Calcula as calorias queimadas (0,04 kcal por passo)
        double caloriasQueimadas = stepCount * 0.04;
        kcalText.setText(String.format("%.2f kcal", caloriasQueimadas)); // Atualiza o TextView das calorias queimadas
    }

    private void saveStepCount(int count) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(STEP_COUNT_KEY, count);
        editor.apply(); // Salva as preferências
    }

    private void saveInitialStepCount(int count) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(INITIAL_STEP_COUNT_KEY, count);
        editor.apply(); // Salva o valor inicial
    }

    private void setupMidnightResetAlarm() {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), MidnightResetReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Configura o calendário para meia-noite
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // Se já passou da meia-noite, configura para o próximo dia
        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // Configura o alarm manager para repetir diariamente à meia-noite
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }
    public void setStepCount(int steps) {
        this.stepCount = steps;
    }

    public int getCircularProgress() {
        return (int) ((stepCount / (float) STEP_GOAL) * 100);
    }

    public String getStepsText() {
        return String.valueOf(stepCount);
    }

}
