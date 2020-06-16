package wizley.android.playground.viewbinding;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import wizley.android.playground.databinding.ActivityViewbindingBinding;

// 컴파일 시에 layout과 binding이 되는지 확인하기 때문에 null로부터 안전하고 유형에 대한 캐스팅 오류가 발생하지 않음
// 다만 레이아웃 변수나 표현식을 지원하지 않아서 동적으로 UI 콘텐츠를 선언하는데 사용할 수 없으며 양방향 데이터 결합을 지원하지 않음
public class ViewBindingActivity extends Activity {
    // layout file's name is used
    private ActivityViewbindingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewbindingBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

        binding.textView.setText("TextView");
        binding.button.setText("Button");
    }
}
