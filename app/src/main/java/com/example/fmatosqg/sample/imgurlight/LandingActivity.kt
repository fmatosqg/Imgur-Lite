package com.example.fmatosqg.sample.imgurlight

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.fmatosqg.sample.imgurlight.PostListAdapter.PostViewHolder
import kotlinx.android.synthetic.main.landing_activity.*
import org.joda.time.format.DateTimeFormat
import java.lang.ref.WeakReference

class LandingActivity : AppCompatActivity() {

    private lateinit var postAdapter: PostListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_activity)

        setupUi()
    }
    ////////////////////////////////////////////
    // end of overriden methods


    private fun setupUi() {

        landing_list.layoutManager = LinearLayoutManager(this)

        postAdapter = PostListAdapter()
        landing_list.adapter = postAdapter

        landing_swipe.setOnRefreshListener {
            // TODO
        }
    }
}

class PostListAdapter() : RecyclerView.Adapter<PostViewHolder>() {
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        holder.setData()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.post_card, parent, false)

        val vh = PostViewHolder(v)
        return vh
    }


    override fun getItemCount(): Int {
        return 5
    }


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        companion object {
            private val formatter = DateTimeFormat.forPattern("dd MMM YYYY")
        }

        private val viewContext = itemView.context
        private val imgBackground: ImageView = itemView.findViewById(R.id.post_card_img_background)


        fun setData() {

            imgBackground.setImageResource(R.drawable.singer)
        }
    }

}