package com.example.demofir;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_user_name,et_psw;//用户名和密码控件变量
    private Button btn_login,btn_register;//按钮变量
    private String userName,psw,spPsw;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
    }

    /***获取界面控件*/
    private void initView() {
        et_user_name=findViewById(R.id.et_user_name);
        et_psw=findViewById(R.id.et_psw);

        btn_login = (Button) findViewById(R.id.btn_login);//绑定登录按钮
        btn_register = (Button) findViewById(R.id.btn_register);//绑定注册按钮

        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        //开始登录，获取用户名和密码 getText().toString().strim();
        String userName = et_user_name.getText().toString().trim();
        String psw = et_psw.getText().toString().trim();

        switch (v.getId()){
                case R.id.btn_login:
                    if(TextUtils.isEmpty(userName)){
                        showMsg("请检查账户密码是否输入...");
                    }else if (TextUtils.isEmpty(psw)){
                        showMsg("请检查账户密码是否输入...");
                    }else
                    setBtn_login(userName,psw);
                    break;
                case R.id.btn_register:
                    setBtn_register(userName,psw);
                    break;
            }

    }

    /***显示信息提示方法*/
    private void showMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /***登录方法*/
    private void setBtn_login(String userName ,String psw){
        //对当前用户输入的密码进行MD5加密再进行比对判断, MD5Utils.md5( ); psw 进行加密判断是否一致
        String md5Psw = MD5Utils.md5(psw);
        // md5Psw ; spPsw 为 根据从SharedPreferences中用户名读取密码
        // 定义方法 readPsw为了读取用户名，得到密码
        spPsw = readPsw(userName);
        android.app.AlertDialog dialog;
//        if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(psw)) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
//
//                    .setTitle("账户密码不能为空")       //设置对话框的标题
//                    .setIcon(R.mipmap.ic_launcher)      //设置对话框标题图标
//                    .setMessage("请输入账户和密码")      //设置对话框的提示信
//                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    })
//                    //添加“取消”按钮
//                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//            dialog = builder.create();
//            dialog.show();
//        }else
       if (md5Psw.equals(spPsw)){
            //一致登陆成功
            showMsg("登陆成功！");
            //保存登录状态，在界面保存登录的用户名 定义个方法 saveLoginStatus boolean 状态 , userName 用户名;
            saveLoginStatus(true, userName);
            //登录成功后关闭此页面进入主页
            Intent data=new Intent();
            //datad.putExtra( ); name , value ;
            data.putExtra("isLogin",true);
            //RESULT_OK为Activity系统常量，状态码为-1
            // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
            setResult(RESULT_OK,data);
            //销毁登录界面
            MainActivity.this.finish();
            //跳转到主界面，登录成功的状态传递到 MainActivity3 中
            startActivity(new Intent(MainActivity.this, MainActivity3.class));
            return;
        }else if ((spPsw!=null&&!TextUtils.isEmpty(spPsw)&&!md5Psw.equals(spPsw))){
            showMsg("密码错误！");
            return;
        }else{
            showMsg("此用户不存在！");
        }

    }


    /***注册方法*/
    private void setBtn_register(String userName ,String psw){
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MainActivity2.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
        startActivity(intent);
    }


    /***
     *注册方法需要的成员类
     */
    /***从SharedPreferences中根据用户名读取密码*/
    private String readPsw(String userName){
        //getSharedPreferences("loginInfo",MODE_PRIVATE);
        //"loginInfo",mode_private; MODE_PRIVATE表示可以继续写入
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //sp.getString() userName, "";
        return sp.getString(userName , "");
    }

    /**保存登录状态和登录用户名到SharedPreferences中*/
    private void saveLoginStatus(boolean status,String userName){
        //saveLoginStatus(true, userName);
        //loginInfo表示文件名  SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取编辑器
        SharedPreferences.Editor editor=sp.edit();
        //存入boolean类型的登录状态
        editor.putBoolean("isLogin", status);
        //存入登录状态时的用户名
        editor.putString("loginUserName", userName);
        //提交修改
        editor.commit();
    }
    /**
     * 注册成功的数据返回至此
     * @param requestCode 请求码
     * @param resultCode 结果码
     * @param data 数据
     */
    @Override
    //显示数据， onActivityResult
    //startActivityForResult(intent, 1); 从注册界面中获取数据
    //int requestCode , int resultCode , Intent data
    // LoginActivity -> startActivityForResult -> onActivityResult();
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            //是获取注册界面回传过来的用户名
            // getExtra().getString("***");
            String userName=data.getStringExtra("userName");
            if(!TextUtils.isEmpty(userName)){
                //设置用户名到 et_user_name 控件
                et_user_name.setText(userName);
                //et_user_name控件的setSelection()方法来设置光标位置
                et_user_name.setSelection(userName.length());
            }
        }
    }
}