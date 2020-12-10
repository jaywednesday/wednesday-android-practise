package com.wednesdaykotlinpractise.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wednesdaykotlinpractise.R
import com.wednesdaykotlinpractise.adapter.FavBankListAdapter
import com.wednesdaykotlinpractise.db.DBHelper
import com.wednesdaykotlinpractise.models.Bank
import kotlinx.android.synthetic.main.fragment_fav_bank.view.*


class FavBankFragment : Fragment() {
    lateinit var fragmentView: View
    lateinit var dbHelper: DBHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dbHelper = DBHelper(activity as Context)
        return inflater.inflate(R.layout.fragment_fav_bank, container, false)
    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentView = view
        initRecyclerView()
    }
    fun initRecyclerView() {
        val bankList = dbHelper.getAllBanks()
        fragmentView.rvFavBankList.layoutManager = LinearLayoutManager(activity as Context)
        fragmentView.rvFavBankList.adapter = FavBankListAdapter(activity as Context, bankList)
    }
    fun deleteRecord(bank:Bank) {
        dbHelper.deleteBank(bank.id!!)
        initRecyclerView()
    }
}