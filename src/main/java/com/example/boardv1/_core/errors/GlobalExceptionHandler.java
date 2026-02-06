package com.example.boardv1._core.errors;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.boardv1._core.errors.ex.*;

@RestControllerAdvice // 예외만을 처리하는 컨트롤러
public class GlobalExceptionHandler {
    // 유효성 검사 실패
    @ExceptionHandler(exception = Exception400.class) // 어떤 예외인지 지정하기
    public String ex400(Exception400 e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, e.getMessage());
        // 로그 남기기
        return html;
    }

    // 미인증 유저
    @ExceptionHandler(exception = Exception401.class) // 어떤 예외인지 지정하기
    public String ex(Exception401 e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    location.href = '/login-form';
                </script>
                """, e.getMessage());
        // 로그 남기기
        return html;
    }

    // 403 - 권한 미달
    @ExceptionHandler(exception = Exception403.class) // 어떤 예외인지 지정하기
    public String ex(Exception403 e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    location.href = '/login-form';
                </script>
                """, e.getMessage());
        // 로그 남기기
        return html;
    }

    // 자원 찾기 실패
    @ExceptionHandler(exception = Exception404.class) // 어떤 예외인지 지정하기
    public String ex(Exception404 e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, e.getMessage());
        // 로그 남기기
        return html;
    }

    // 서버 오류
    @ExceptionHandler(exception = Exception500.class) // 어떤 예외인지 지정하기
    public String ex(Exception500 e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, "관리자에게 문의하십시오."); // 500 에러는 클라이언트에게 노출 금지
        // 로그 남기기
        return html;
    }
}
