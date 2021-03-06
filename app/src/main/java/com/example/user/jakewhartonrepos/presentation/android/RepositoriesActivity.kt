package com.example.user.jakewhartonrepos.presentation.android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.user.jakewhartonrepos.JakeWhartonReposApp
import com.example.user.jakewhartonrepos.R
import com.example.user.jakewhartonrepos.data.model.GithubRepositoryModel
import com.example.user.jakewhartonrepos.presentation.presenter.Repositories.RepositoriesPresenter
import com.example.user.jakewhartonrepos.presentation.view.Repositories.RepositoriesView

class RepositoriesActivity : MvpAppCompatActivity(), RepositoriesView, RepositoryModelRecyclerViewAdapter.OnRepositoryItemInteractionListener {
    companion object {
        const val TAG = "RepositoriesActivity"
        fun getIntent(context: Context): Intent = Intent(context, RepositoriesActivity::class.java)
    }

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRecyclerViewAdapter: RepositoryModelRecyclerViewAdapter
    @InjectPresenter
    lateinit var mRepositoriesPresenter: RepositoriesPresenter

    @ProvidePresenter
    fun provideRepositoriesPresenter(): RepositoriesPresenter {
        Log.d("JakeWhartonRepos", "Provide RepositoriesPresenter ")
        return JakeWhartonReposApp.get().getRepositoriesActivityComponent().provideRepositoriesPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            mRepositoriesPresenter.onRefreshClick()
        }

        mRecyclerView = findViewById(R.id.repositories_list) as RecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        mRecyclerViewAdapter = RepositoryModelRecyclerViewAdapter(ArrayList<GithubRepositoryModel>(), this)
        mRecyclerView.adapter = mRecyclerViewAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_repositories, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        mRepositoriesPresenter.onAttach()
        super.onResume()
    }

    override fun onDestroy() {
        mRepositoriesPresenter.onDetach()
        super.onDestroy()
    }

    /**
     * Methods implemented from {@link RepositoriesView}
     * */

    override fun showRepoInList(gitHubRepo: GithubRepositoryModel) {
        mRecyclerViewAdapter.mValues.add(gitHubRepo) //To change body of created functions use File | Settings | File Templates.
        mRecyclerViewAdapter.notifyItemInserted(mRecyclerViewAdapter.mValues.size)
    }

    override fun refresh() {
        mRepositoriesPresenter.onRefreshClick()
    }

    override fun clearRepositoriesList() {
        mRecyclerViewAdapter.mValues.clear()
        mRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        findViewById(R.id.loading_view).visibility = View.VISIBLE
    }

    override fun showErrorMessage() {
        Log.d("JakeWhartonRepos", "showErrorMessage")
        Toast.makeText(this, "Some error occur", Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        findViewById(R.id.loading_view).visibility = View.GONE
    }

    /**
     *  Methods implemented from {@link RepositoryModelRecyclerViewAdapter.OnRepositoryItemInteractionListener}
     * */
    override fun onRepositoryItemInteraction(item: GithubRepositoryModel) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
