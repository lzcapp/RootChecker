package top.rainysummer.rootchecker;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lahm.library.EasyProtectorLib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isRoot = EasyProtectorLib.checkIsRoot();
        boolean isXposed = EasyProtectorLib.checkIsXposedExist();
        boolean isEmulator = EasyProtectorLib.checkIsRunningInEmulator(this, emulatorInfo -> {
        });

        ImageView imageView = findViewById(R.id.imageView);

        if (isRoot) {
            imageView.setImageResource(R.drawable.seele);
        } else if (isXposed) {
            imageView.setImageResource(R.drawable.ex);
        } else if (isEmulator) {
            imageView.setImageResource(R.drawable.phone);
        } else {
            imageView.setImageResource(R.drawable.yes);
        }

        TextView txtRoot = findViewById(R.id.txtRoot);
        TextView txtRoot2 = findViewById(R.id.txtRoot2);
        TextView txtXposed = findViewById(R.id.txtXposed);
        TextView txtXposed2 = findViewById(R.id.txtXposed2);
        TextView txtEmulator = findViewById(R.id.txtEmulator);
        TextView txtEmulator2 = findViewById(R.id.txtEmulator2);
        String strRoot, strRoot2, strXposed, strXposed2, strEmulator, strEmulator2, strSu;
        if (isRoot) {
            strRoot = "\u274E <b><tt>Root</tt></b>: ";
            strRoot2 = "<font color=\"#FF4444\">" + getResources().getText(R.string.RootY) + "</font>";
        } else {
            strRoot = "\u2705 <b><tt>Root</tt></b>: ";
            strRoot2 = "<font color=\"#99CC00\">" + getResources().getText(R.string.RootN) + "</font>";
        }
        if (isXposed) {
            strXposed = "\u274E <b><tt>Xposed</tt></b>: ";
            strXposed2 = "<font color=\"#FF4444\">" + getResources().getText(R.string.XposedY) + "</font>";
        } else {
            strXposed = "\u2705 <b><tt>Xposed</tt></b>: ";
            strXposed2 = "<font color=\"#99CC00\">" + getResources().getText(R.string.XposedN) + "</font>";
        }
        if (isEmulator) {
            strEmulator = "\u274E <b><tt>Emulator</tt></b>: ";
            strEmulator2 = "<font color=\"#FF4444\">" + getResources().getText(R.string.EmulatorY) + "</font>";
        } else {
            strEmulator = "\u2705 <b><tt>Emulator</tt></b>: ";
            strEmulator2 = "<font color=\"#99CC00\">" + getResources().getText(R.string.EmulatorN) + "</font>";
        }
        if (isRoot) {
            strSu = whichSU();
            strRoot2 += " (" + strSu + ")";
        }
        txtRoot.setText(Html.fromHtml(strRoot));
        txtRoot2.setText(Html.fromHtml(strRoot2));
        txtXposed.setText(Html.fromHtml(strXposed));
        txtXposed2.setText(Html.fromHtml(strXposed2));
        txtEmulator.setText(Html.fromHtml(strEmulator));
        txtEmulator2.setText(Html.fromHtml(strEmulator2));

        TextView txtStatus = findViewById(R.id.txtStatus);



        if (isRoot || isXposed || isEmulator) {
            txtStatus.setText(Html.fromHtml("<font color=\"#FF4444\">Failed!</font>"));
        } else {
            txtStatus.setText(Html.fromHtml("<font color=\"#99CC00\">Passed!</font>"));
        }
    }

    public static ArrayList<String> executeCommand(String[] shellCmd){
        String line;
        ArrayList<String> fullResponse = new ArrayList<>();
        Process localProcess;
        try {
            localProcess = Runtime.getRuntime().exec(shellCmd);
        } catch (Exception e) {
            return null;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(localProcess.getInputStream()));
        try {
            while ((line = in.readLine()) != null) {
                fullResponse.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullResponse;
    }

    public static String whichSU() {
        String[] strCmd = new String[] {"/system/xbin/which","su"};
        ArrayList<String> execResult = executeCommand(strCmd);
        if (execResult != null){
            return execResult.get(0);
        }else{
            return null;
        }
    }
}