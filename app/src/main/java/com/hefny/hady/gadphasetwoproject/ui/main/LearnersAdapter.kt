package com.hefny.hady.gadphasetwoproject.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hefny.hady.gadphasetwoproject.R
import com.hefny.hady.gadphasetwoproject.api.responses.Leader
import kotlinx.android.synthetic.main.learner_list_item.view.*

class LearnersAdapter() :
    RecyclerView.Adapter<LearnersAdapter.LearnersViewHolder>() {
    private var myLeaders = ArrayList<Leader>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnersViewHolder {
        return LearnersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.learner_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return myLeaders.size
    }

    override fun onBindViewHolder(holder: LearnersViewHolder, position: Int) {
        holder.bind(myLeaders[position])
    }

    fun setLeadersList(leaders: ArrayList<Leader>) {
        myLeaders = leaders
        notifyDataSetChanged()
    }

    class LearnersViewHolder(myItemView: View) : RecyclerView.ViewHolder(myItemView) {
        fun bind(leader: Leader) {
            Glide.with(itemView.context)
                .load(leader.badgeUrl)
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .into(itemView.badge_image)
            itemView.learner_name_text.text = leader.name
            leader.hours?.let {
                val formattedLearningString =
                    itemView.resources.getString(R.string.learning_hours, it, leader.country)
                itemView.learner_description_text.text = formattedLearningString
            }
            leader.score?.let {
                val formattedSkillIqString =
                    itemView.resources.getString(R.string.skill_iq, it, leader.country)
                itemView.learner_description_text.text = formattedSkillIqString
            }
        }
    }
}