package com.example.chat.kt

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.TextView
import com.example.chat.R

class ComDialog : Dialog {
    var tvTitle: TextView? = null//主题
    var tvDialogContent: TextView? = null//内容
    var btDialogConfirm: Button? = null //确定
    var btDialogCancel: Button? = null //取消


    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, themeStyle: Int) : super(context, themeStyle) {
        initView()
    }

    private fun initView(){
        setContentView(R.layout.activity_com_dialog)
        setCanceledOnTouchOutside(false)
        tvDialogContent=findViewById(R.id.dia_context)
        btDialogConfirm=findViewById(R.id.dia_yes)
        btDialogCancel=findViewById(R.id.dia_no)

    }

    class Builder(val context: Context){
        var confirmListener: OnConfirmListener? = null
        var cancelListener: OnCancelListener? = null
        var title: String? = null
        var content: String? = null
        var btConfirmText: String? = null
        var btCancelText: String? = null



        fun setOnConfirmListener(confirmListener: OnConfirmListener): Builder {
            this.confirmListener = confirmListener
            return this
        }

        fun setOnCancelListener(cancelListener: OnCancelListener): Builder {
            this.cancelListener = cancelListener
            return this
        }
        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }
        fun setContent(content: String): Builder {
            this.content = content
            return this
        }

        // 点击确定按钮的文字
        fun setConfirmText(btConfirmText: String): Builder {
            this.btConfirmText = btConfirmText
            return this
        }

        //点击取消按钮的文字
        fun setCancelText(tvCancelText: String): Builder {
            this.btCancelText = tvCancelText
            return this
        }

        fun create(): ComDialog {
            val dialog= ComDialog(context)
            /*if (!TextUtils.isEmpty(title)){
                dialog.tvTitle?.text=this.title
            }else{
                dialog.tvTitle?.visibility=View.GONE
            }*/

            dialog.btDialogConfirm?.text = this.btConfirmText ?: "确认"
            dialog.btDialogCancel?.text = this.btCancelText ?: "取消"


            if (cancelListener != null) {
                dialog.btDialogCancel?.setOnClickListener { v -> cancelListener!!.onClick(dialog)}
            }
            if (confirmListener != null) {
                dialog.btDialogConfirm?.setOnClickListener { v -> confirmListener!!.onClick(dialog) }
            }
            return dialog
        }

    }
    // 点击弹窗取消按钮回调
    interface OnCancelListener {
        fun onClick(dialog: Dialog)
    }

    // 点击弹窗确定按钮跳转回调
    interface OnConfirmListener  {
         fun onClick(dialog: Dialog)
    }

}