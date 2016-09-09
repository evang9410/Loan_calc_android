package com.eg.loancalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LoanCalculator lc;
    private EditText et_loan_amount, et_term_of_loan, et_yearly_interest_rate;
    private Button btn_calc, btn_clear;
    private TextView tv_monthly_payment, tv_total_payment, tv_total_interest;

    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // init objects into memory.
        init();
        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Null and error check EditText fields
                // or not.

                if(!et_loan_amount.getText().toString().matches("") && !et_term_of_loan.getText().toString().matches("") && !et_yearly_interest_rate.getText().toString().matches("")) {
                    double loan_amount = Double.parseDouble(et_loan_amount.getText().toString());
                    int years = Integer.parseInt(et_term_of_loan.getText().toString());
                    double yearly_interest = Double.parseDouble(et_yearly_interest_rate.getText().toString());

                    // Set LoanCalculator fields
                    lc.setLoanAmount(loan_amount);
                    lc.setNumberOfYears(years);
                    lc.setYearlyInterestRate(yearly_interest);

                    total = lc.getMonthlyPayment();

                    tv_monthly_payment.setText(String.valueOf(lc.getMonthlyPayment()));
                    tv_total_payment.setText(String.valueOf(lc.getTotalCostOfLoan()));
                    tv_total_interest.setText(String.valueOf((double)Math.round(lc.getTotalInterest() * 100) /100));
                }
                else {
                    //Log.d("test", "toast");
                    Toast toast = Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_loan_amount.setText("");
                et_term_of_loan.setText("");
                et_yearly_interest_rate.setText("");
                tv_monthly_payment.setText("");
                tv_total_payment.setText("");
                tv_total_interest.setText("");
            }
        });

        if(savedInstanceState != null){
            total = savedInstanceState.getDouble("total");
            Log.d("savedInstance_total", String.valueOf(total));
        }
        Log.d("total",String.valueOf(total));
    }//onCreate()

    private void init(){
        lc = new LoanCalculator();
        // Buttons
        btn_calc = (Button)findViewById(R.id.btn_calc);
        btn_clear = (Button)findViewById(R.id.btn_clear);
        // EditText
        et_loan_amount = (EditText)findViewById(R.id.et_loan_amount);
        et_term_of_loan = (EditText)findViewById(R.id.et_term_of_loan);
        et_yearly_interest_rate = (EditText)findViewById(R.id.et_yearly_interest);
        //TextView
        tv_monthly_payment = (TextView)findViewById(R.id.tv_rs_monthly_payment);
        tv_total_interest = (TextView)findViewById(R.id.tv_rs_total_interest);
        tv_total_payment = (TextView)findViewById(R.id.tv_rs_total_payment);

    }
    @Override
    protected void  onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d("saved","saved instance state");
        outState.putDouble("total",total);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "onResume()");
    }
}
