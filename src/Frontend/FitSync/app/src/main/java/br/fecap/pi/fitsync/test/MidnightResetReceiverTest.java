//package br.fecap.pi.fitsync.test;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//
//import java.util.Map;
//
//import br.fecap.pi.fitsync.HomeFragment;
//import br.fecap.pi.fitsync.MidnightResetReceiver;
//
//public class MidnightResetReceiverTest {
//
//    public static void main(String[] args) {
//        testOnReceiveResetsStepCount();
//    }
//
//    public static void testOnReceiveResetsStepCount() {
//
//        FakeContext context = new FakeContext();
//
//
//        MidnightResetReceiver receiver = new MidnightResetReceiver();
//
//
//        receiver.onReceive(context, new Intent());
//
//
//        int stepCount = context.getSharedPreferences().getInt(HomeFragment.STEP_COUNT_KEY, -1);
//
//
//        if (stepCount == 0) {
//            System.out.println("Teste passou: Contagem de passos resetada para 0");
//        } else {
//            System.out.println("Teste falhou: Contagem de passos não foi resetada");
//        }
//    }
//
//    // Contexto simulado
//    static class FakeContext extends Context {
//        private FakeSharedPreferences prefs = new FakeSharedPreferences();
//
//        @Override
//        public SharedPreferences getSharedPreferences(String name, int mode) {
//            return prefs;
//        }
//
//        public FakeSharedPreferences getSharedPreferences() {
//            return prefs;
//        }
//    }
//
//    // SharedPreferences simulado
//    static class FakeSharedPreferences implements SharedPreferences {
//        private int stepCount = 100;
//
//        @Override
//        public Editor edit() {
//            return new FakeEditor(this);
//        }
//
//        @Override
//        public int getInt(String key, int defValue) {
//            return key.equals(HomeFragment.STEP_COUNT_KEY) ? stepCount : defValue;
//        }
//
//        // Métodos não usados
//        @Override public boolean contains(String key) { return false; }
//        @Override public Map<String, ?> getAll() { return null; }
//        @Override public String getString(String key, String defValue) { return null; }
//        @Override public boolean getBoolean(String key, boolean defValue) { return false; }
//        @Override public float getFloat(String key, float defValue) { return 0; }
//        @Override public long getLong(String key, long defValue) { return 0; }
//    }
//
//    // Editor simulado
//    static class FakeEditor implements SharedPreferences.Editor {
//        private FakeSharedPreferences prefs;
//
//        public FakeEditor(FakeSharedPreferences prefs) {
//            this.prefs = prefs;
//        }
//
//        @Override
//        public SharedPreferences.Editor putInt(String key, int value) {
//            if (key.equals(HomeFragment.STEP_COUNT_KEY)) {
//                prefs.stepCount = value;
//            }
//            return this;
//        }
//
//        @Override public boolean commit() { return true; }
//        @Override public void apply() { }
//
//        @Override public SharedPreferences.Editor putString(String key, String value) { return this; }
//        @Override public SharedPreferences.Editor putLong(String key, long value) { return this; }
//        @Override public SharedPreferences.Editor putFloat(String key, float value) { return this; }
//        @Override public SharedPreferences.Editor putBoolean(String key, boolean value) { return this; }
//        @Override public SharedPreferences.Editor remove(String key) { return this; }
//        @Override public SharedPreferences.Editor clear() { return this; }
//    }
//}
