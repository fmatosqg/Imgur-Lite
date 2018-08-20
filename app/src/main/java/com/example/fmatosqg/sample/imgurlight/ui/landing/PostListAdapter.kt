package com.example.fmatosqg.sample.imgurlight.ui.landing

import android.support.annotation.UiThread
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.fmatosqg.sample.imgurlight.R
import org.joda.time.format.DateTimeFormat

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
                PostCardViewModel("", 0, 0,
                        0, 0, 0)
            }
        }
    }

    @UiThread
    fun setData(dataList: List<PostCardViewModel>) {
        synchronized(this.dataList) {
            this.dataList.clear()
            this.dataList.addAll(dataList)
        }

        notifyDataSetChanged()
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
            // TODO this format looks better than DD/MM/YYYY h:mm a, double check
            private val formatter = DateTimeFormat.forPattern("dd/MM/YYYY H:mm a")
        }

        private val viewContext = itemView.context
        private val imgBackground: ImageView = itemView.findViewById(R.id.post_card_img_background)
        private val txtDate: TextView = itemView.findViewById(R.id.post_card_txt_date)
        private val txtSubtitle: TextView = itemView.findViewById(R.id.post_card_txt_subtitle)


        fun setData(postCardViewModel: PostCardViewModel) {

            with(postCardViewModel) {


                imgBackground.visibility = if (imgUrl.isNotBlank()) {

                    imgBackground.setImageResource(R.drawable.imgur_fake_logo)

                    Glide.with(viewContext)
                            .load(imgUrl)
                            .into(imgBackground)


                    View.VISIBLE
                } else {
                    View.GONE
                }

                txtDate.visibility =
                        if (dateMs != 0L) {
                            txtDate.text = formatter.print(dateMs)
                            View.VISIBLE
                        } else {
                            View.GONE
                        }


                txtSubtitle.text =
                        if (imgAdditionalCount == 0) {
                            viewContext.resources.getString(R.string.post_card_subtitle)
                        } else {
                            viewContext.resources.getQuantityString(R.plurals.post_card_subtitle_plurals, imgAdditionalCount, imgAdditionalCount)
                        }
            }
        }
    }

}

data class PostCardViewModel(
        val imgUrl: String,
        val dateMs: Long,
        val imgAdditionalCount: Int,

        val points: Long,
        val score: Long,
        val topicId: Long
) {
    fun isFilterTrue(): Boolean {

        return (points + score + topicId) % 2 == 0L

    }
}