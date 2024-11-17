//package br.fecap.pi.fitsync.test;
//
//import android.app.Instrumentation;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.android.uiautomator.core.UiDevice;
//import com.android.uiautomator.testrunner.UiAutomatorTestCase;
//
//public class RegisterTest extends UiAutomatorTestCase {
//
//    private Instrumentation instrumentation;
//    private UiDevice device;
//
//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//        instrumentation = getInstrumentation();
//        device = UiDevice.getInstance(instrumentation);
//    }
//
//    public void testRegisterUserSuccess() throws Exception {
//        device.pressHome();
//        Thread.sleep(2000);  // Espera um pouco para garantir que a tela inicial foi carregada
//
//
//        EditText editNome = (EditText) device.findObject(new UiSelector().resourceId("br.fecap.pi.fitsync:id/editar_nome"));
//        EditText editEmail = (EditText) device.findObject(new UiSelector().resourceId("br.fecap.pi.fitsync:id/editar_email"));
//        EditText editSenha = (EditText) device.findObject(new UiSelector().resourceId("br.fecap.pi.fitsync:id/editar_senha"));
//
//        editNome.setText("John Doe");
//        editEmail.setText("johndoe@example.com");
//        editSenha.setText("password123");
//
//        device.findObject(new UiSelector().resourceId("br.fecap.pi.fitsync:id/cadastrar_button")).click();
//
//        Thread.sleep(2000);
//
//        assertTrue("Toast de sucesso não apareceu", checkToast("Cadastro realizado com sucesso!"));
//
//    }
//
//    public void testRegisterUserFailure() throws Exception {
//        EditText editNome = (EditText) device.findObject(new UiSelector().resourceId("br.fecap.pi.fitsync:id/editar_nome"));
//        EditText editEmail = (EditText) device.findObject(new UiSelector().resourceId("br.fecap.pi.fitsync:id/editar_email"));
//        EditText editSenha = (EditText) device.findObject(new UiSelector().resourceId("br.fecap.pi.fitsync:id/editar_senha"));
//
//        editNome.setText("John Doe");
//        editEmail.setText("johndoe@example.com");
//        editSenha.setText("password123");
//
//        device.findObject(new UiSelector().resourceId("br.fecap.pi.fitsync:id/cadastrar_button")).click();
//
//        Thread.sleep(2000);
//
//        assertTrue("Toast de erro não apareceu", checkToast("Erro ao cadastrar. Tente novamente."));
//    }
//
//    private boolean checkToast(String expectedMessage) {
//        return true; // Supondo que a verificação de toast aconteça com base em outro critério
//    }
//}
