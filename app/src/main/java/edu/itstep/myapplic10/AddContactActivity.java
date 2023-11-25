package edu.itstep.myapplic10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {
    private EditText etFullName, etPhone, etCity, etStreet, etHouseNumber, etApartmentNumber;
    private Button btnAddContact;

    private ContactDAO contactDAO;
    private AddressDAO addressDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        etFullName = findViewById(R.id.etFullName);
        etPhone = findViewById(R.id.etPhone);
        etCity = findViewById(R.id.etCity);
        etStreet = findViewById(R.id.etStreet);
        etHouseNumber = findViewById(R.id.etHouseNumber);
        etApartmentNumber = findViewById(R.id.etApartmentNumber);
        btnAddContact = findViewById(R.id.btnAddContact);

        contactDAO = App.getInstance().getContactDAO();
        addressDAO = App.getInstance().getAddressDAO();

        btnAddContact.setOnClickListener(view -> addContact());
    }

    private void addContact() {
        String fullName = etFullName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String city = etCity.getText().toString().trim();
        String street = etStreet.getText().toString().trim();
        String houseNumber = etHouseNumber.getText().toString().trim();
        String apartmentNumber = etApartmentNumber.getText().toString().trim();

        Address address = new Address(city, street, houseNumber, apartmentNumber);
        long addressId = addressDAO.insert(address);

        Contact contact = new Contact(fullName, phone, addressId);
        contactDAO.insert(contact);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("contact", contact);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
