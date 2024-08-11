package com.example.axforassets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemsPageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemsAdapter itemsAdapter;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_page);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();
        populateItemList();

        itemsAdapter = new ItemsAdapter(itemList, new ItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                Intent intent = new Intent(ItemsPageActivity.this, ItemDetail.class);

                // Pass item details including image names
                intent.putExtra("title", item.getTitle());
                intent.putExtra("category", item.getCategory());
                intent.putExtra("desc", item.getDesc());
                intent.putExtra("longDesc", item.getLongDesc());
                intent.putExtra("price", item.getPrice());
                intent.putExtra("releaseDate", item.getReleaseDate());

                // Pass image resource names based on item title
                String imageGifName = item.getTitle().toLowerCase();
                String imagePngName = item.getTitle().toLowerCase() + "header";

                intent.putExtra("imageGifName", imageGifName);
                intent.putExtra("imagePngName", imagePngName);

                startActivity(intent);
            }
        });



        recyclerView.setAdapter(itemsAdapter);

        onClickListenerNav();
    }

    private void populateItemList() {
        itemList.add(new Item("Torch", "Adventure", "Lights up dark areas and wards off monsters. Essential for adventurer!", "Torch is a vital light source in Minecraft, crucial for illuminating dark caves, preventing mob spawns, and guiding players through nighttime adventures. Crafted easily from sticks and coal or charcoal, torches are indispensable for exploration and safety.", "Rp 200,000", "31/01/2024"));
        itemList.add(new Item("Bow", "Weapon", "Ranged weapon for attacking enemies from a distance. Reliable!", "The bow is a versatile ranged weapon in Minecraft, allowing players to engage enemies from a distance with accuracy and efficiency. Crafted from sticks and string, bows can be enchanted to enhance their abilities, such as increasing damage or granting infinite arrows.", "Rp 30,000", "03/05/2022"));
        itemList.add(new Item("Allay", "Pet", "Helpful mob that collects items. Great for gathering and organization!", "The Allay is a helpful mob introduced in recent updates, notable for its ability to collect and deliver items to players. By assigning specific items for the Allay to gather, players can streamline resource management and inventory organization during their Minecraft adventures.", "Rp 40,000", "09/12/2021"));
        itemList.add(new Item("Diamond", "Commodity", "Rare gem for crafting powerful tools and armor. Valuable and durable!", "Diamonds are prized gems in Minecraft, known for their rarity and superior crafting properties. Used primarily to create high-performance tools and armor, diamonds offer unparalleled durability and efficiency in mining and combat scenarios.", "Rp 500,000", "05/10/2022"));
        itemList.add(new Item("Pig", "Pet", "Source of pork and can be ridden. Great for farming and breeding!", "Pigs are friendly and useful animals in Minecraft, providing players with pork for sustenance and a means of transportation when saddled. They can be bred using carrots, potatoes, or beetroots to maintain a sustainable source of food and resources.", "Rp 340,000", "19/06/2023"));
    }

//    protected void onClickListenerItems(){
//        View hotspot_torch = findViewById(R.id.transparentOverlay_1);
//        View hotspot_bow = findViewById(R.id.transparentOverlay_2);
//        View hotspot_allay = findViewById(R.id.transparentOverlay_3);
//        View hotspot_diamond = findViewById(R.id.transparentOverlay_4);
//        View hotspot_pig = findViewById(R.id.transparentOverlay_5);
//
//        hotspot_torch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ItemDetailData.getInstance().setCategory("Adventure");
//                ItemDetailData.getInstance().setDesc("Lights up dark areas and wards off monsters. Essential for adventurer");
//                ItemDetailData.getInstance().setLongDesc("Torch is a vital light source in Minecraft, crucial for illuminating dark caves, preventing mob spawns, and guiding players through nighttime adventures. Crafted easily from sticks and coal or charcoal, torches are indispensable for exploration and safety.");
//                ItemDetailData.getInstance().setPrice("Rp 200,000");
//                ItemDetailData.getInstance().setTitle("Torch");
//                ItemDetailData.getInstance().setReleaseDate("31/01/2024");
//                Intent intent = new Intent(ItemsPageActivity.this, LoginPageActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        hotspot_bow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ItemDetailData.getInstance().setCategory("Weapon");
//                ItemDetailData.getInstance().setDesc("Ranged weapon for attacking enemies from a distance. Reliable!");
//                ItemDetailData.getInstance().setLongDesc("The bow is a versatile ranged weapon in Minecraft, allowing players to engage enemies from a distance with accuracy and efficiency. Crafted from sticks and string, bows can be enchanted to enhance their abilities, such as increasing damage or granting infinite arrows.");
//                ItemDetailData.getInstance().setPrice("Rp 30.000");
//                ItemDetailData.getInstance().setTitle("Bow");
//                ItemDetailData.getInstance().setReleaseDate("03/05/2022");
//                Intent intent = new Intent(ItemsPageActivity.this, LoginPageActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        hotspot_allay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ItemDetailData.getInstance().setCategory("Pet");
//                ItemDetailData.getInstance().setDesc("Helpful mob that collects items. Great for gathering and organization!");
//                ItemDetailData.getInstance().setLongDesc("The Allay is a helpful mob introduced in recent updates, notable for its ability to collect and deliver items to players. By assigning specific items for the Allay to gather, players can streamline resource management and inventory organization during their Minecraft adventures.");
//                ItemDetailData.getInstance().setPrice("Rp 40.000");
//                ItemDetailData.getInstance().setTitle("Allay");
//                ItemDetailData.getInstance().setReleaseDate("09/12/2021");
//                Intent intent = new Intent(ItemsPageActivity.this, LoginPageActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        hotspot_diamond.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ItemDetailData.getInstance().setCategory("Commodity");
//                ItemDetailData.getInstance().setDesc("Rare gem for crafting powerful tools and armor. Valuable and durable!");
//                ItemDetailData.getInstance().setLongDesc("Diamonds are prized gems in Minecraft, known for their rarity and superior crafting properties. Used primarily to create high-performance tools and armor, diamonds offer unparalleled durability and efficiency in mining and combat scenarios.");
//                ItemDetailData.getInstance().setPrice("Rp 500.000");
//                ItemDetailData.getInstance().setTitle("Diamond");
//                ItemDetailData.getInstance().setReleaseDate("05/10/2022");
//                Intent intent = new Intent(ItemsPageActivity.this, LoginPageActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        hotspot_pig.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ItemDetailData.getInstance().setCategory("Pet");
//                ItemDetailData.getInstance().setDesc("Source of pork and can be ridden. Great for farming and breeding!");
//                ItemDetailData.getInstance().setLongDesc("Pigs are friendly and useful animals in Minecraft, providing players with pork for sustenance and a means of transportation when saddled. They can be bred using carrots, potatoes, or beetroots to maintain a sustainable source of food and resources.");
//                ItemDetailData.getInstance().setPrice("Rp 340.000");
//                ItemDetailData.getInstance().setTitle("Pig");
//                ItemDetailData.getInstance().setReleaseDate("19/06/2023");
//                Intent intent = new Intent(ItemsPageActivity.this, LoginPageActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

    protected void onClickListenerNav(){
        ImageView hamburgerMenu = findViewById(R.id.hamburgerMenu);
        ConstraintLayout nav = findViewById(R.id.nav);

        FrameLayout outsideNav = findViewById(R.id.backgroundFrame);

        TextView homeNav = findViewById(R.id.homeNav);
        TextView profileNav = findViewById(R.id.profileNav);
        TextView logoutNav = findViewById(R.id.logoutNav);

        hamburgerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nav.setVisibility(View.VISIBLE);
                outsideNav.setVisibility(View.VISIBLE);
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
                Intent intent = new Intent(ItemsPageActivity.this, LoginPageActivity.class);
                startActivity(intent);
            }
        });

        profileNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemsPageActivity.this, LoginPageActivity.class);
                startActivity(intent);
            }
        });

        logoutNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemsPageActivity.this, LoginPageActivity.class);
                startActivity(intent);
            }
        });
    }


}