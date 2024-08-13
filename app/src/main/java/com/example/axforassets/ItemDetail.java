package com.example.axforassets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

public class ItemDetail extends AppCompatActivity {

    private EditText emailInput;
    private Spinner paymentMethodSpinner;
    private Button buyButton;
    private CustomSpinnerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        // Get the passed data
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String category = intent.getStringExtra("category");
        String desc = intent.getStringExtra("desc");
        String longDesc = intent.getStringExtra("longDesc");
        String price = intent.getStringExtra("price");
        String releaseDate = intent.getStringExtra("releaseDate");
        String imageGifName = intent.getStringExtra("imageGifName");
        String imagePngName = intent.getStringExtra("imagePngName");

        // Set the item details
        TextView titleTextView = findViewById(R.id.itemTitle);
        titleTextView.setText(title);

        TextView categoryTextView = findViewById(R.id.itemDetails4);
        categoryTextView.setText(category);

        TextView descTextView = findViewById(R.id.itemDescription2);
        descTextView.setText(longDesc);

        TextView priceTextView = findViewById(R.id.itemPrice);
        priceTextView.setText(price);

        TextView releaseDateTextView = findViewById(R.id.itemDetails2);
        releaseDateTextView.setText(releaseDate);

        // Set the images
        int imageGifResId = getResources().getIdentifier(imageGifName, "drawable", getPackageName());
        ImageView imageitem1 = findViewById(R.id.imageitem1);
        imageitem1.setImageResource(imageGifResId);

        int imagePngResId = getResources().getIdentifier(imagePngName, "drawable", getPackageName());
        ImageView itemImage = findViewById(R.id.itemImage);
        itemImage.setImageResource(imagePngResId);

        // Back button functionality
        ImageView backButton = findViewById(R.id.arrow);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



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

        buyButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        buyButton.setBackgroundColor(getResources().getColor(R.color.buyButton2));
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        buyButton.setBackgroundColor(getResources().getColor(R.color.buybButton1));
                        break;
                }
                return false;
            }
        });


        // Handle button click
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndShowDialog();
            }
        });

        onClickListenerNav();
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
                Intent intent = new Intent(ItemDetail.this, ItemsPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        dialog.show();
    }

    protected void onClickListenerNav() {
        ImageView hamburgerMenu = findViewById(R.id.hamburgerMenu);
        ConstraintLayout nav = findViewById(R.id.nav);
        FrameLayout outsideNav = findViewById(R.id.backgroundFrame);

        TextView homeNav = findViewById(R.id.homeNav);
        TextView profileNav = findViewById(R.id.profileNav);
        TextView logoutNav = findViewById(R.id.logoutNav);

        TextView textWelcome = findViewById(R.id.textWelcome);

        hamburgerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nav.getVisibility() == View.VISIBLE) {
                    nav.setVisibility(View.GONE);
                    outsideNav.setVisibility(View.GONE);
                } else {
                    nav.setVisibility(View.VISIBLE);
                    outsideNav.setVisibility(View.VISIBLE);
                }
            }
        });

        outsideNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nav.setVisibility(View.GONE);
                outsideNav.setVisibility(View.GONE);
            }
        });

        homeNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nav.setVisibility(View.GONE);
                outsideNav.setVisibility(View.GONE);
                Intent intent = new Intent(ItemDetail.this, HomeActivity.class);
                intent.putExtra("EXTRA_USERNAME", UserData.getInstance().getUsername());
                startActivity(intent);
            }
        });

        profileNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nav.setVisibility(View.GONE);
                outsideNav.setVisibility(View.GONE);
                Intent intent = new Intent(ItemDetail.this, LoginPageActivity.class);
                startActivity(intent);
            }
        });

        logoutNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nav.setVisibility(View.GONE);
                outsideNav.setVisibility(View.GONE);
                Intent intent = new Intent(ItemDetail.this, LoginPageActivity.class);
                startActivity(intent);
            }
        });
    }


}
