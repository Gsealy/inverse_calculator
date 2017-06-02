package cn.studyjams.s2.sj141.inverse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FloatingActivity.class);
                startActivity(intent);
            }
        });

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                Intent it = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(it);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void myEuclid(int e, int modValue) {
        int x = e;
        int num = e;
        int d = 1;
        while ((num % modValue) != 1) {
            d++;
            num += e;
        }
        //System.out.println(d);
        String s = String.valueOf(d);
        EditText invre = (EditText) findViewById(R.id.etMulInvResult);
        invre.setText(s);
    }

    public int maxCommonDivisor(int m, int n) {

        if (m < n) {// 保证m>n,若m<n,则进行数据交换
            int temp = m;
            m = n;
            n = temp;
        }
        while (m % n != 0) {// 在余数不能为0时,进行循环
            int temp = m % n;
            m = n;
            n = temp;
        }
        int result1 = n;
        String q = String.valueOf(result1);
        EditText gcd = (EditText) findViewById(R.id.etGCDResult);
        gcd.setText(q);
        return n;// 返回最大公约数
    }

    // 求最小公倍数
    public int minCommonMultiple(int m, int n) {
        int r1 = m * n / maxCommonDivisor(m, n);
        String wqe = String.valueOf(r1);
        EditText lcm = (EditText) findViewById(R.id.etLCMResult);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(lcm));
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        lcm.setText(wqe);
        return r1;
    }

    public void calanswer(View view) {
        EditText texta = (EditText) findViewById(R.id.etMumA);
        EditText textb = (EditText) findViewById(R.id.etMumB);

        String valuea = texta.getText().toString();
        String valueb = textb.getText().toString();
        if (valuea.equals("") || valueb.equals("")) {
            Snackbar.make(view, "请完整入a和b", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else {
            int a = Integer.valueOf(valuea).intValue();
            int b = Integer.valueOf(valueb).intValue();
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(a));
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(b));
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            if (a > b || b % a == 0) {
                Snackbar.make(view, "请重新输入a和b", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            } else {
                myEuclid(a, b);
                maxCommonDivisor(a, b);
                minCommonMultiple(a, b);

            }
        }
    }

    public void reset(View view) {
        EditText texta = (EditText) findViewById(R.id.etMumA);
        EditText textb = (EditText) findViewById(R.id.etMumB);
        EditText lcm = (EditText) findViewById(R.id.etLCMResult);
        EditText gcd = (EditText) findViewById(R.id.etGCDResult);
        EditText invre = (EditText) findViewById(R.id.etMulInvResult);
        invre.setText("");
        gcd.setText("");
        lcm.setText("");
        texta.setText("");
        textb.setText("");
    }
}
