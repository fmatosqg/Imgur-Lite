package com.example.fmatosqg.sample.imgurlight.ui.landing

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.fmatosqg.sample.imgurlight.R
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.concurrent.ConcurrentLinkedQueue

class PostListAdapter : RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    private val dataList = mutableListOf<PostCardViewModel>()


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        holder.setData(getData(position))

    }

    private fun getData(position: Int): PostCardViewModel {

        synchronized(dataList) {
            return if (dataList.size > position)
                dataList[position]
            else {
                PostCardViewModel("", 0, 0)
            }
        }
    }

    fun setData(dataList: List<PostCardViewModel>) {
        synchronized(this.dataList) {
            this.dataList.clear()
            this.dataList.addAll(dataList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.post_card, parent, false)

        val vh = PostViewHolder(v)
        return vh
    }


    override fun getItemCount(): Int {
        synchronized(dataList) {
            return dataList.size
        }
    }


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        companion object {
            private val formatter = DateTimeFormat.forPattern("dd/MM/YYYY h:mm a")
        }

        private val viewContext = itemView.context
        private val imgBackground: ImageView = itemView.findViewById(R.id.post_card_img_background)
        private val txtDate: TextView = itemView.findViewById(R.id.post_card_txt_date)
        private val txtSubtitle: TextView = itemView.findViewById(R.id.post_card_txt_subtitle)


        fun setData(postCardViewModel: PostCardViewModel) {

            with(postCardViewModel) {

                Glide.with(viewContext)
                        .load(imgUrl)
                        .into(imgBackground)

                txtDate.text = formatter.print(dateMs)
                txtSubtitle.text = viewContext.resources.getQuantityString(R.plurals.post_card_subtitle, imgCount, imgCount)
            }
        }
    }

}

data class PostCardViewModel(
        val imgUrl: String,
        val dateMs: Long,
        val imgCount: Int
)