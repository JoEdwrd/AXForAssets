package com.example.axforassets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetail extends AppCompatActivity {

    private EditText emailInput;
    private Spinner paymentMethodSpinner;
    private Button buyButton;
    private CustomSpinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        emailInput = findViewById(R.id.emailInput);
        paymentMethodSpinner = findViewById(R.id.paymentMethodSpinner);
        buyButton = findViewById(R.id.buyButton);

        // Initialize the custom adapter
        adapter = new CustomSpinnerAdapter(this,
                R.layout.spinner_item,
                getResources().getTextArray(R.array.payment_methods));
        adapter.setDropDownViewResource(R.layout.spinner_item);
        paymentMethodSpinner.setAdapter(adapter);

        // Handle spinner item selection
        paymentMethodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // Do nothing if the default item is selected
                    return;
                }

                if (adapter.isFirstTime()) { // Access using the public getter
                    adapter.setFirstTime(false);
                    adapter.notifyDataSetChanged();
                    paymentMethodSpinner.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });

        // Handle button click
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndShowDialog();
            }
        });
    }

    private void validateAndShowDialog() {
        String email = emailInput.getText().toString().trim();
        String paymentMethod = paymentMethodSpinner.getSelectedItem().toString();

        if (email.isEmpty()) {
            showCustomDialog("Email must be filled", R.drawable.ic_warning);
            return;
        }

        if (!email.contains("@gmail.com")) {
            showCustomDialog("Email must be a valid Gmail address", R.drawable.ic_warning);
            return;
        }

        if (paymentMethod.equals("Select payment method")) { // Assuming first item is a prompt
            showCustomDialog("Please select a payment method", R.drawable.ic_warning);
            return;
        }

        showConfirmationDialog(email);
    }

    private void showCustomDialog(String message, int iconResId) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this, R.style.CustomDialog);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_custom, null);
        dialogBuilder.setView(dialogView);

        ImageView dialogIcon = dialogView.findViewById(R.id.dialogIcon);
        dialogIcon.setImageResource(iconResId);

        TextView dialogMessage = dialogView.findViewById(R.id.dialogMessage);
        dialogMessage.setText(message);

        final AlertDialog dialog = dialogBuilder.create();

        Button dialogButton = dialogView.findViewById(R.id.dialogButton);
        dialogButton.setText("Try Again");
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showConfirmationDialog(String email) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this, R.style.CustomDialog);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_custom, null);
        dialogBuilder.setView(dialogView);

        ImageView dialogIcon = dialogView.findViewById(R.id.dialogIcon);
        dialogIcon.setImageResource(R.drawable.ic_success);

        TextView dialogMessage = dialogView.findViewById(R.id.dialogMessage);
        dialogMessage.setText("Confirmation email has been sent to " + email);

        final AlertDialog dialog = dialogBuilder.create();

        Button dialogButton = dialogView.findViewById(R.id.dialogButton);
        dialogButton.setText("Go to Item Page");
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemDetail.this, NextActivity.class); // Replace NextActivity with your next activity
                startActivity(intent);
                finish();
            }
        });

        dialog.show();
    }
}
