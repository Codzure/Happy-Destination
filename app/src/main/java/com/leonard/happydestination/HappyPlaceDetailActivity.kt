package com.leonard.happydestination

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.leonard.happydestination.models.HappyPlaceModel
import kotlinx.android.synthetic.main.activity_happy_place_detail.*

class HappyPlaceDetailActivity : AppCompatActivity() {

    private var saveImageToInternalStorage: Uri? = null

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_happy_place_detail)

        var happyPlaceDetailModel: HappyPlaceModel? = null

        if (intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)) {
            // get the Serializable data model class with the details in it
            happyPlaceDetailModel =
                intent.getSerializableExtra(MainActivity.EXTRA_PLACE_DETAILS) as HappyPlaceModel
        }

        if (happyPlaceDetailModel != null) {

            setSupportActionBar(toolbar_happy_place_detail)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = happyPlaceDetailModel.title

            toolbar_happy_place_detail.setNavigationOnClickListener {
                onBackPressed()
            }

            tv_description.text = happyPlaceDetailModel.description

            iv_place_image.setImageURI(Uri.parse(happyPlaceDetailModel.image))
            iv_place_image.visibility = View.VISIBLE
            tv_location.text = happyPlaceDetailModel.location
            tv_location.visibility = View.VISIBLE
            btn_view_on_map.visibility = View.VISIBLE
        }

        btn_view_on_map.setOnClickListener {
            val intent = Intent(this@HappyPlaceDetailActivity, MapActivity::class.java)
            intent.putExtra(MainActivity.EXTRA_PLACE_DETAILS, happyPlaceDetailModel)
            startActivity(intent)
        }
    }
}