package com.example.user.jakewhartonrepos.ui.activity.Repositories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.user.jakewhartonrepos.R
import com.example.user.jakewhartonrepos.domain.GitDataRepositoriesImpl
import com.example.user.jakewhartonrepos.domain.datasource.MockGithubDataSource
import com.example.user.jakewhartonrepos.model.GithubRepositoryModel
import com.example.user.jakewhartonrepos.presentation.presenter.Repositories.RepositoriesPresenter
import com.example.user.jakewhartonrepos.presentation.view.Repositories.RepositoriesView


class RepositoriesActivity : MvpAppCompatActivity(), RepositoriesView, RepositoryModelRecyclerViewAdapter.OnRepositoryItemInteractionListener {
    companion object {
        const val TAG = "RepositoriesActivity"
        fun getIntent(context: Context): Intent = Intent(context, RepositoriesActivity::class.java)
    }

    @InjectPresenter
    lateinit var mRepositoriesPresenter: RepositoriesPresenter

    @ProvidePresenter
    fun provideRepositoriesPresenter(): RepositoriesPresenter {
        return RepositoriesPresenter(GitDataRepositoriesImpl(MockGithubDataSource()))
    }

    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mRecyclerViewAdapter: RepositoryModelRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        mRecyclerView = findViewById(R.id.repositories_list) as RecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(this)
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

    override fun onRepositoryItemInteraction(item: GithubRepositoryModel) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRepoInList(gitHubRepo: GithubRepositoryModel) {
        mRecyclerViewAdapter.mValues.add(gitHubRepo) //To change body of created functions use File | Settings | File Templates.
        mRecyclerViewAdapter.notifyItemInserted(mRecyclerViewAdapter.mValues.size)
    }

    override fun showErrorMessage() {
        Toast.makeText(this,"Some error occur",Toast.LENGTH_SHORT)
    }

    override fun showCompleteMessage() {
        Toast.makeText(this,"Loading complete",Toast.LENGTH_SHORT)
    }

    override fun onResume() {
        super.onResume()
        mRepositoriesPresenter.onAttach()
    }
}
