package com.wednesdaykotlinpractise.fragment

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.wednesdaykotlinpractise.AppController
import com.wednesdaykotlinpractise.R
import com.wednesdaykotlinpractise.db.DBHelper
import com.wednesdaykotlinpractise.models.Bank
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.item_bank.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment(), View.OnClickListener {
    private lateinit var bank: Bank
    private lateinit var dbHelper: DBHelper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        dbHelper = DBHelper(activity as Context)
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSearch.setOnClickListener(this)
        btnAddToFavBank.setOnClickListener(this)
    }
    fun resetText() {
        tvBankName.text = ""
        tvIFSC.text = ""
        tvBranch.text = ""
        tvAddress.text = ""
        tvContact.text = ""
        tvState.text = ""
        tvDistrict.text = ""
        tvRTGS.text = ""
        tvCity.text = ""
    }
    fun searchBank() {
        btnAddToFavBank.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        resetText()
        val searchBankCall: Call<Bank> = AppController.instance.apiInterface.searchBank(edtSearchBank.text.toString())
        searchBankCall.enqueue(object: Callback<Bank> {
            override fun onResponse(call: Call<Bank>, response: Response<Bank>) {
                btnSearch.isClickable = true
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        bank = response.body()!!
                        tvBankName.text = "Bank Name: ${bank.bank}"
                        tvIFSC.text = "IFSC Code: ${bank.ifsc}"
                        tvBranch.text = "Branch: ${bank.branch}"
                        tvAddress.text = "Address: ${bank.address}"
                        tvContact.text = "Contact: ${bank.contact}"
                        tvState.text = "State: ${bank.state}"
                        tvDistrict.text = "State: ${bank.district}"
                        tvRTGS.text = "RTGS: ${bank.rtgs}"
                        tvCity.text = "City: ${bank.city}"
                        btnAddToFavBank.visibility = View.VISIBLE
                    }
                } else {
                    tvBankName.text = "Not Found"
                }
            }

            override fun onFailure(call: Call<Bank>, t: Throwable) {
                btnSearch.isClickable = true
                progressBar.visibility = View.GONE
                tvBankName.text = "Error while searching"
            }

        })
    }
    override fun onClick(view: View?) {
        when(view) {
            btnSearch -> {
                if(edtSearchBank.text.isNullOrEmpty()) {
                    Toast.makeText(activity, "Please type IFSC Code", Toast.LENGTH_LONG).show()
                } else {
                    btnSearch.isClickable = false
                    searchBank()
                }
            }
            btnAddToFavBank -> {
                if (dbHelper.doesBankExistIfsc(bank.ifsc!!)) {
                    Toast.makeText(
                        activity,
                        "Bank already added to the fav list",
                        Toast.LENGTH_LONG
                    ).show();
                } else {
                    val cv = ContentValues()
                    cv.put(DBHelper.KEY_BANK, bank.bank)
                    cv.put(DBHelper.KEY_IFSC, bank.ifsc)
                    cv.put(DBHelper.KEY_BRANCH, bank.branch)
                    cv.put(DBHelper.KEY_ADDRESS, bank.address)
                    cv.put(DBHelper.KEY_CONTACT, bank.contact)
                    cv.put(DBHelper.KEY_RTGS, bank.rtgs)
                    cv.put(DBHelper.KEY_DISTRICT, bank.district)
                    cv.put(DBHelper.KEY_STATE, bank.state)
                    cv.put(DBHelper.KEY_CITY, bank.city)
                    val success = DBHelper(activity as Context).createBank(cv);
                    if (success > 0) {
                        Toast.makeText(
                            activity,
                            "Bank successfully added to the Fav list",
                            Toast.LENGTH_LONG
                        ).show();
                    }
                }
            }
        }
    }
}