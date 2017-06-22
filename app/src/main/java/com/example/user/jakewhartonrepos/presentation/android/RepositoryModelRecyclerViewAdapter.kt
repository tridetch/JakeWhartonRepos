package com.example.user.jakewhartonrepos.presentation.android

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.user.jakewhartonrepos.R
import com.example.user.jakewhartonrepos.data.model.GithubRepositoryModel

class RepositoryModelRecyclerViewAdapter(val mValues: ArrayList<GithubRepositoryModel>, private val mListener: RepositoryModelRecyclerViewAdapter
.OnRepositoryItemInteractionListener) : RecyclerView.Adapter<RepositoryModelRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.github_repo_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository: GithubRepositoryModel = mValues[position]
        holder.mItem = repository
        holder.mRepoName.text = repository.name
        holder.mRepoDescription.text = repository.description
        holder.mRepoStars.text = repository.stars.toString()

        holder.mView.setOnClickListener {
            mListener.onRepositoryItemInteraction(holder.mItem as GithubRepositoryModel)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: GithubRepositoryModel? = null
        val mRepoName: TextView = mView.findViewById(R.id.repository_name) as TextView
        val mRepoDescription: TextView = mView.findViewById(R.id.repository_description) as TextView
        val mRepoStars: TextView = mView.findViewById(R.id.repository_stars) as TextView

        override fun toString(): String {
            return super.toString() + " '" + mRepoDescription.text + "'"
        }
    }

    interface OnRepositoryItemInteractionListener {
        // TODO: Update argument type and name
        fun onRepositoryItemInteraction(item: GithubRepositoryModel)
    }

}
