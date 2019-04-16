package cn.com.food.measure.ui.login;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.com.food.measure.R;
import cn.com.food.measure.base.BaseActivity;
import cn.com.food.measure.base.BaseConfig;
import cn.com.food.measure.net.HttpCallback;
import cn.com.food.measure.net.HttpModel;
import cn.com.food.measure.net.HttpResult;
import cn.com.food.measure.ui.main.MainActivity;
import cn.com.food.measure.util.SharedPreUtil;
import cn.com.food.measure.util.ToastUtil;

public class LoginActivity extends BaseActivity {

    private TextView tvLoginIP;
    private EditText etUserName;
    private EditText etPwd;
    private Button btnLogin;
    private CheckBox cbPwdSee;
    private CheckBox cbRemember;

    private String userName;
    private String userPwd;

    @Override
    protected Settings setActivitySettings(View contentView) {
        return new Settings();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(View contentView) {
        tvLoginIP = contentView.findViewById(R.id.tv_login_ip);
        etUserName = contentView.findViewById(R.id.et_login_username);
        etPwd = contentView.findViewById(R.id.et_login_pwd);
        btnLogin = contentView.findViewById(R.id.btn_login);
        cbPwdSee = contentView.findViewById(R.id.cb_login_pwd_see);
        cbRemember = contentView.findViewById(R.id.cb_login_remember);
    }

    @Override
    protected void bindEvent(View contentView) {
        cbPwdSee.setOnCheckedChangeListener(onCheckedChange);
        cbRemember.setOnCheckedChangeListener(onCheckedChange);
        tvLoginIP.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        cbPwdSee.setChecked(false);
        cbRemember.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_ip:
                Intent intent = new Intent(this, IpActivity.class);
                intent.putExtra(BaseConfig.PARAM_IP, BaseConfig.RETURN_RESULT_LOGIN);
                startActivityForResult(intent, BaseConfig.PARAM_LOGIN_IP);
                break;
            case R.id.btn_login:
                loginMethod();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BaseConfig.PARAM_LOGIN_IP) {
            if (resultCode == BaseConfig.RETURN_CONFIG_IP) {

            }
        }
    }

    CompoundButton.OnCheckedChangeListener onCheckedChange = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.cb_login_pwd_see:
                    if (isChecked) {
                        etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        etPwd.setSelection(etPwd.getText().length());
                    } else {
                        etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        etPwd.setSelection(etPwd.getText().length());
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void loginMethod() {
        userName = etUserName.getText().toString();
        userPwd = etPwd.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            ToastUtil.showSingleToast(R.string.login_name_hint, Toast.LENGTH_SHORT);
        } else if (TextUtils.isEmpty(userPwd)) {
            ToastUtil.showSingleToast(R.string.login_pwd_hint, Toast.LENGTH_SHORT);
        } else {
            HttpModel.getInstance().postLogin(userName, userPwd, new HttpCallback(this) {
                @Override
                public void onSuccessStr(HttpResult httpResult) {
                    try {
                        if (cbRemember.isChecked()) {
                            SharedPreUtil.saveLogin(true);
                        }
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
