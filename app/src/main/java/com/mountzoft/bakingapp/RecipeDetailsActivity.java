package com.mountzoft.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsActivity extends AppCompatActivity {

    String RECIPE_POSITION = "RECIPE_POSITION";

    LinearLayoutManager mLinearLayoutManager;
    RecipeDetailsRecyclerViewAdapter adapter;

    int recipePosition;
    List<Ingredient> mIngredients;

    @BindView(R.id.rv_recipe_detials)
    RecyclerView mRecipeRecyclerView;

    @BindView(R.id.tv_ingridents)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        ButterKnife.bind(this);

        mTextView = findViewById(R.id.tv_ingridents);

        recipePosition = getIntent().getExtras().getInt(RECIPE_POSITION);

        setTitle(RecipeActivity.recipeDataList.get(recipePosition).getName());

        mIngredients = RecipeActivity.recipeDataList.get(recipePosition).getIngredients();
        for(int i =0; i<mIngredients.size(); i++){
            mTextView.append("\u2022 "+ mIngredients.get(i).getIngredient()+"\n");
            mTextView.append(getString(R.string.quantity)+mIngredients.get(i).getQuantity().toString()+"\n");
            mTextView.append(getString(R.string.measure)+mIngredients.get(i).getMeasure()+"\n\n");
        }

        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecipeRecyclerView.setLayoutManager(mLinearLayoutManager);
        adapter = new RecipeDetailsRecyclerViewAdapter(this, recipePosition);
        mRecipeRecyclerView.setAdapter(adapter);
    }
}
