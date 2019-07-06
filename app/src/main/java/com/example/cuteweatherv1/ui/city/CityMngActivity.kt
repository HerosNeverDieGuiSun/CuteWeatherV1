package com.example.cuteweatherv1.ui.city

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuteweatherv1.R
import com.example.cuteweatherv1.adapter.city.CityAdapter
import com.example.cuteweatherv1.adapter.city.CityItemClickListener
import com.example.cuteweatherv1.repository.city.CityRepository
import com.example.cuteweatherv1.repository.city.data.CityInfo
import kotlinx.android.synthetic.main.activity_city_mng.*
import android.widget.TextView
import android.graphics.Color
import com.example.cuteweatherv1.location.MyLocation


/**
 * Created by 胡婵娟.
 * 内容：城市管理页面处理
 */
class CityMngActivity : AppCompatActivity() {
    lateinit var adapter:CityAdapter
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        setContentView(R.layout.activity_city_mng)
        //添加当前定位城市
        CityRepository.instance.data.add(
            CityInfo(MyLocation.instance.city.value!!, "","","","","","","")
        )

        val saveData = sharedPreferences.getString("city", null)
        if (saveData != null){
            val city = saveData.split(",".toRegex())
            for(i in city) {
                CityRepository.instance.data.add(
                    CityInfo(i, "", "", "", "", "", "", "")
                )
            }
        }

        adapter = CityAdapter(
            applicationContext,
            R.layout.city_item,
            CityRepository.instance.data,
            object: CityItemClickListener{
                override fun onItemClick(position: Int) {
                    //点击事件
                    val intentBack = Intent()
                    intentBack.putExtra("cityName", CityRepository.instance.data[position].name)
                    setResult(2, intentBack)
                    finish()
                }

                override fun onItemLongClick(position: Int): Boolean {
                    //长按事件
                    val builder = AlertDialog.Builder(this@CityMngActivity)
                        .setTitle("您确定要删除此条城市信息吗？")
                        .setPositiveButton("删除", object : DialogInterface.OnClickListener{
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                if (position != 0) {
                                    CityRepository.instance.data.removeAt(position)

                                    var content: StringBuilder = StringBuilder()
                                    if (CityRepository.instance.data.size > 1) {
                                        for (i in 1..CityRepository.instance.data.size-1) {
                                            content.append(CityRepository.instance.data[i].name)
                                            content.append(",")
                                        }
                                        content.deleteCharAt(content.length - 1)
                                        val edit = sharedPreferences.edit()
                                        edit.putString("city", content.toString()).commit()
                                    }
                                    else {
                                        val edit = sharedPreferences.edit()
                                        edit.remove("city").commit()
                                    }
                                    adapter.notifyDataSetChanged()
                                } else {
                                    Toast.makeText(applicationContext, "不可以删除当前定位城市哦", Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                        .setNegativeButton("取消", object : DialogInterface.OnClickListener{
                            override fun onClick(p0: DialogInterface?, p1: Int) {

                            }
                        })
                    val dialog = builder.create()
                    dialog.show()
                    //设置弹窗字体和颜色
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(15f)
                    dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextSize(15f)
                    try {
                        val mAlert = AlertDialog::class.java.getDeclaredField("mAlert")
                        mAlert.isAccessible = true
                        val mAlertController = mAlert.get(dialog)
                        //通过反射修改title字体大小和颜色
                        val mTitle = mAlertController.javaClass.getDeclaredField("mTitleView")
                        mTitle.isAccessible = true
                        val mTitleView = mTitle.get(mAlertController) as TextView
                        mTitleView.textSize = 100f
                        mTitleView.setTextColor(Color.BLACK)
                    } catch (e1: IllegalAccessException) {
                        e1.printStackTrace()
                    } catch (e2: NoSuchFieldException) {
                        e2.printStackTrace()
                    }

                    return true
                }
            }
        )

        city_rv.adapter = adapter

        city_rv.layoutManager = LinearLayoutManager(applicationContext)

        add_btn.setOnClickListener {
            val intent = Intent()
            intent.setClass(applicationContext, CityChooseActivity::class.java)

            startActivity(intent)
        }

        city_back.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        CityRepository.instance.data.clear()
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

}
