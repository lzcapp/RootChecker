package top.rainysummer.rootchecker;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.scottyab.rootbeer.RootBeer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> executeCommand(String[] shellCmd) {
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
        ArrayList<String> execResult = executeCommand(new String[]{"which", "su"});
        if (execResult != null && !execResult.isEmpty()) {
            return execResult.get(0);
        } else {
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RootBeer rootBeer = new RootBeer(this);
        boolean isRoot = rootBeer.isRooted();
        boolean isRootedWithBusyBox = rootBeer.isRootedWithBusyBoxCheck();

        ImageView imageView = findViewById(R.id.imageView);

        if (isRoot || isRootedWithBusyBox) {
            imageView.setImageResource(R.drawable.seele);
        }

        TextView txtRoot = findViewById(R.id.txtRoot);
        TextView txtRoot2 = findViewById(R.id.txtRoot2);
        TextView txtXposed = findViewById(R.id.txtXposed);
        TextView txtXposed2 = findViewById(R.id.txtXposed2);
        TextView txtEmulator = findViewById(R.id.txtEmulator);
        TextView txtEmulator2 = findViewById(R.id.txtEmulator2);
        String strRoot, strRoot2, strXposed, strXposed2, strEmulator, strEmulator2, strSu;
        if (isRoot || isRootedWithBusyBox) {
            strRoot = "❎ <b><tt>Root</tt></b>: ";
            strRoot2 = "<font color=\"#FF4444\">" + getResources().getText(R.string.RootY) + "</font>";
        } else {
            strRoot = "✅ <b><tt>Root</tt></b>: ";
            strRoot2 = "<font color=\"#99CC00\">" + getResources().getText(R.string.RootN) + "</font>";
        }
        if (isRoot || isRootedWithBusyBox) {
            strSu = whichSU();
            if (strSu != null) {
                strRoot2 += " (" + strSu + ")";
            } else {
                strRoot2 += "";
            }
        }
        txtRoot.setText(Html.fromHtml(strRoot));
        txtRoot2.setText(Html.fromHtml(strRoot2));

        TextView txtStatus = findViewById(R.id.txtStatus);


        if (isRoot || isRootedWithBusyBox) {
            txtStatus.setText("FAILED!");
            txtStatus.setTextColor(Color.parseColor("#FF4444"));
        } else {
            txtStatus.setText("PASSED!");
            txtStatus.setTextColor(Color.parseColor("#99CC00"));
        }
    }
}