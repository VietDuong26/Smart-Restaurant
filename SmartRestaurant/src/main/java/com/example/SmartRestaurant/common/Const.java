package com.example.SmartRestaurant.common;

public class Const {
    public static final String PREFIX_VERSION = "/v1/api";

    public static final String HTML_OTP_MAIL = "<!DOCTYPE html>\n" +
            "<html lang=\"vi\">\n" +
            "<head>\n" +
            "  <meta charset=\"UTF-8\" />\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
            "  <title>Xác nhận OTP – Smart Restaurant</title>\n" +
            "  <style>\n" +
            "    body {\n" +
            "      margin: 0;\n" +
            "      padding: 0;\n" +
            "      background-color: #F5F4EF;\n" +
            "      font-family: Georgia, 'Times New Roman', serif;\n" +
            "    }\n" +
            "    .wrapper {\n" +
            "      padding: 40px 16px;\n" +
            "    }\n" +
            "    .card {\n" +
            "      max-width: 560px;\n" +
            "      margin: 0 auto;\n" +
            "      background: #ffffff;\n" +
            "      border-radius: 12px;\n" +
            "      overflow: hidden;\n" +
            "      border: 1px solid #E0DED8;\n" +
            "    }\n" +
            "    .header {\n" +
            "      background: #1A1A1A;\n" +
            "      padding: 32px 40px 24px;\n" +
            "      text-align: center;\n" +
            "    }\n" +
            "    .header-logo {\n" +
            "      display: inline-flex;\n" +
            "      align-items: center;\n" +
            "      gap: 10px;\n" +
            "      margin-bottom: 6px;\n" +
            "    }\n" +
            "    .header-logo span {\n" +
            "      font-size: 20px;\n" +
            "      font-weight: 700;\n" +
            "      letter-spacing: 0.04em;\n" +
            "      color: #C8A96E;\n" +
            "    }\n" +
            "    .header-sub {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 11px;\n" +
            "      color: #888888;\n" +
            "      letter-spacing: 0.1em;\n" +
            "      text-transform: uppercase;\n" +
            "      margin: 0;\n" +
            "    }\n" +
            "    .body {\n" +
            "      padding: 32px 40px;\n" +
            "    }\n" +
            "    .greeting {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 15px;\n" +
            "      color: #1A1A1A;\n" +
            "      margin: 0 0 6px;\n" +
            "    }\n" +
            "    .intro {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 14px;\n" +
            "      color: #555555;\n" +
            "      line-height: 1.7;\n" +
            "      margin: 0 0 28px;\n" +
            "    }\n" +
            "    .otp-box {\n" +
            "      background: #F5F4EF;\n" +
            "      border-radius: 8px;\n" +
            "      padding: 24px;\n" +
            "      text-align: center;\n" +
            "      margin-bottom: 24px;\n" +
            "      border: 1px solid #E0DED8;\n" +
            "    }\n" +
            "    .otp-label {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 11px;\n" +
            "      color: #888888;\n" +
            "      letter-spacing: 0.08em;\n" +
            "      text-transform: uppercase;\n" +
            "      margin: 0 0 12px;\n" +
            "    }\n" +
            "    .otp-code {\n" +
            "      font-family: 'Courier New', Courier, monospace;\n" +
            "      font-size: 36px;\n" +
            "      font-weight: 700;\n" +
            "      letter-spacing: 0.2em;\n" +
            "      color: #1A1A1A;\n" +
            "      margin: 0 0 14px;\n" +
            "    }\n" +
            "    .otp-expiry {\n" +
            "      display: inline-flex;\n" +
            "      align-items: center;\n" +
            "      gap: 6px;\n" +
            "      background: #FEF3DC;\n" +
            "      border-radius: 999px;\n" +
            "      padding: 5px 16px;\n" +
            "    }\n" +
            "    .otp-expiry span {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 12px;\n" +
            "      font-weight: 500;\n" +
            "      color: #854F0B;\n" +
            "    }\n" +
            "    .notice {\n" +
            "      border-left: 3px solid #E0DED8;\n" +
            "      padding: 10px 16px;\n" +
            "      margin-bottom: 28px;\n" +
            "    }\n" +
            "    .notice p {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 13px;\n" +
            "      color: #777777;\n" +
            "      margin: 0;\n" +
            "      line-height: 1.6;\n" +
            "    }\n" +
            "    .sign-off {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 14px;\n" +
            "      color: #777777;\n" +
            "      margin: 0 0 4px;\n" +
            "    }\n" +
            "    .sign-name {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 14px;\n" +
            "      font-weight: 600;\n" +
            "      color: #1A1A1A;\n" +
            "      margin: 0;\n" +
            "    }\n" +
            "    .footer {\n" +
            "      border-top: 1px solid #E0DED8;\n" +
            "      padding: 20px 40px;\n" +
            "      text-align: center;\n" +
            "    }\n" +
            "    .footer p {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 11px;\n" +
            "      color: #AAAAAA;\n" +
            "      margin: 0 0 4px;\n" +
            "      line-height: 1.6;\n" +
            "    }\n" +
            "    .footer p:last-child {\n" +
            "      margin: 0;\n" +
            "    }\n" +
            "  </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "  <div class=\"wrapper\">\n" +
            "    <div class=\"card\">\n" +
            "\n" +
            "      <!-- HEADER -->\n" +
            "      <div class=\"header\">\n" +
            "        <div class=\"header-logo\">\n" +
            "          <svg width=\"28\" height=\"28\" viewBox=\"0 0 28 28\" fill=\"none\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "            <circle cx=\"14\" cy=\"14\" r=\"14\" fill=\"#C8A96E\"/>\n" +
            "            <path d=\"M8 18 Q10 11 14 10 Q18 11 20 18\" stroke=\"#1A1A1A\" stroke-width=\"1.5\" fill=\"none\" stroke-linecap=\"round\"/>\n" +
            "            <circle cx=\"14\" cy=\"9\" r=\"2\" fill=\"#1A1A1A\"/>\n" +
            "            <path d=\"M10 18 Q14 15 18 18\" stroke=\"#1A1A1A\" stroke-width=\"1.2\" fill=\"none\" stroke-linecap=\"round\"/>\n" +
            "          </svg>\n" +
            "          <span>Smart Restaurant</span>\n" +
            "        </div>\n" +
            "        <p class=\"header-sub\">Xác nhận tài khoản</p>\n" +
            "      </div>\n" +
            "\n" +
            "      <!-- BODY -->\n" +
            "      <div class=\"body\">\n" +
            "        <p class=\"greeting\">Xin chào <strong>%s</strong>,</p>\n" +
            "        <p class=\"intro\">\n" +
            "          Chào mừng bạn đến với <strong>Smart Restaurant</strong>! Để hoàn tất đăng ký tài khoản,\n" +
            "          vui lòng sử dụng mã xác thực dưới đây.\n" +
            "        </p>\n" +
            "\n" +
            "        <!-- OTP BOX -->\n" +
            "        <div class=\"otp-box\">\n" +
            "          <p class=\"otp-label\">Mã xác thực OTP</p>\n" +
            "          <p class=\"otp-code\">%s</p>\n" +
            "          <div class=\"otp-expiry\">\n" +
            "            <svg width=\"13\" height=\"13\" viewBox=\"0 0 13 13\" fill=\"none\">\n" +
            "              <circle cx=\"6.5\" cy=\"6.5\" r=\"5.75\" stroke=\"#854F0B\" stroke-width=\"1.2\"/>\n" +
            "              <path d=\"M6.5 3.5v3.5l2 1.5\" stroke=\"#854F0B\" stroke-width=\"1.2\" stroke-linecap=\"round\"/>\n" +
            "            </svg>\n" +
            "            <span>Hết hạn sau 5 phút</span>\n" +
            "          </div>\n" +
            "        </div>\n" +
            "\n" +
            "        <!-- SECURITY NOTICE -->\n" +
            "        <div class=\"notice\">\n" +
            "          <p>\n" +
            "            Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email và liên hệ với chúng tôi\n" +
            "            ngay nếu bạn cho rằng tài khoản bị truy cập trái phép.\n" +
            "          </p>\n" +
            "        </div>\n" +
            "\n" +
            "        <p class=\"sign-off\">Trân trọng,</p>\n" +
            "        <p class=\"sign-name\">Đội ngũ Smart Restaurant</p>\n" +
            "      </div>\n" +
            "\n" +
            "      <!-- FOOTER -->\n" +
            "      <div class=\"footer\">\n" +
            "        <p>Email này được gửi tự động, vui lòng không trả lời.</p>\n" +
            "        <p>© 2026 Smart Restaurant. All rights reserved.</p>\n" +
            "      </div>\n" +
            "\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</body>\n" +
            "</html>";

    public static final String HTML_RESET_PASSWORD_MAIL = "<!DOCTYPE html>\n" +
            "<html lang=\"vi\">\n" +
            "<head>\n" +
            "  <meta charset=\"UTF-8\" />\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
            "  <title>Đặt lại mật khẩu – Smart Restaurant</title>\n" +
            "  <style>\n" +
            "    body {\n" +
            "      margin: 0;\n" +
            "      padding: 0;\n" +
            "      background-color: #F5F4EF;\n" +
            "      font-family: Georgia, 'Times New Roman', serif;\n" +
            "    }\n" +
            "    .wrapper {\n" +
            "      padding: 40px 16px;\n" +
            "    }\n" +
            "    .card {\n" +
            "      max-width: 560px;\n" +
            "      margin: 0 auto;\n" +
            "      background: #ffffff;\n" +
            "      border-radius: 12px;\n" +
            "      overflow: hidden;\n" +
            "      border: 1px solid #E0DED8;\n" +
            "    }\n" +
            "    .header {\n" +
            "      background: #1A1A1A;\n" +
            "      padding: 32px 40px 24px;\n" +
            "      text-align: center;\n" +
            "    }\n" +
            "    .header-logo {\n" +
            "      display: inline-flex;\n" +
            "      align-items: center;\n" +
            "      gap: 10px;\n" +
            "      margin-bottom: 6px;\n" +
            "    }\n" +
            "    .header-logo span {\n" +
            "      font-size: 20px;\n" +
            "      font-weight: 700;\n" +
            "      letter-spacing: 0.04em;\n" +
            "      color: #C8A96E;\n" +
            "    }\n" +
            "    .header-sub {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 11px;\n" +
            "      color: #888888;\n" +
            "      letter-spacing: 0.1em;\n" +
            "      text-transform: uppercase;\n" +
            "      margin: 0;\n" +
            "    }\n" +
            "    .body {\n" +
            "      padding: 32px 40px;\n" +
            "    }\n" +
            "    .greeting {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 15px;\n" +
            "      color: #1A1A1A;\n" +
            "      margin: 0 0 6px;\n" +
            "    }\n" +
            "    .intro {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 14px;\n" +
            "      color: #555555;\n" +
            "      line-height: 1.7;\n" +
            "      margin: 0 0 28px;\n" +
            "    }\n" +
            "    .reset-box {\n" +
            "      background: #F5F4EF;\n" +
            "      border-radius: 8px;\n" +
            "      padding: 28px 24px;\n" +
            "      text-align: center;\n" +
            "      margin-bottom: 24px;\n" +
            "      border: 1px solid #E0DED8;\n" +
            "    }\n" +
            "    .reset-icon {\n" +
            "      margin-bottom: 16px;\n" +
            "    }\n" +
            "    .reset-label {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 11px;\n" +
            "      color: #888888;\n" +
            "      letter-spacing: 0.08em;\n" +
            "      text-transform: uppercase;\n" +
            "      margin: 0 0 16px;\n" +
            "    }\n" +
            "    .reset-btn {\n" +
            "      display: inline-block;\n" +
            "      background: #1A1A1A;\n" +
            "      color: #C8A96E;\n" +
            "      text-decoration: none;\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 14px;\n" +
            "      font-weight: 600;\n" +
            "      letter-spacing: 0.06em;\n" +
            "      padding: 14px 36px;\n" +
            "      border-radius: 6px;\n" +
            "      margin-bottom: 16px;\n" +
            "    }\n" +
            "    .reset-expiry {\n" +
            "      display: inline-flex;\n" +
            "      align-items: center;\n" +
            "      gap: 6px;\n" +
            "      background: #FEF3DC;\n" +
            "      border-radius: 999px;\n" +
            "      padding: 5px 16px;\n" +
            "      margin-top: 4px;\n" +
            "    }\n" +
            "    .reset-expiry span {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 12px;\n" +
            "      font-weight: 500;\n" +
            "      color: #854F0B;\n" +
            "    }\n" +
            "    .fallback {\n" +
            "      margin-bottom: 24px;\n" +
            "    }\n" +
            "    .fallback p {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 12px;\n" +
            "      color: #888888;\n" +
            "      margin: 0 0 6px;\n" +
            "    }\n" +
            "    .fallback-link {\n" +
            "      font-family: 'Courier New', Courier, monospace;\n" +
            "      font-size: 11px;\n" +
            "      color: #555555;\n" +
            "      word-break: break-all;\n" +
            "      background: #F5F4EF;\n" +
            "      border: 1px solid #E0DED8;\n" +
            "      border-radius: 4px;\n" +
            "      padding: 8px 12px;\n" +
            "      display: block;\n" +
            "      line-height: 1.6;\n" +
            "    }\n" +
            "    .notice {\n" +
            "      border-left: 3px solid #E0DED8;\n" +
            "      padding: 10px 16px;\n" +
            "      margin-bottom: 28px;\n" +
            "    }\n" +
            "    .notice p {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 13px;\n" +
            "      color: #777777;\n" +
            "      margin: 0;\n" +
            "      line-height: 1.6;\n" +
            "    }\n" +
            "    .sign-off {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 14px;\n" +
            "      color: #777777;\n" +
            "      margin: 0 0 4px;\n" +
            "    }\n" +
            "    .sign-name {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 14px;\n" +
            "      font-weight: 600;\n" +
            "      color: #1A1A1A;\n" +
            "      margin: 0;\n" +
            "    }\n" +
            "    .footer {\n" +
            "      border-top: 1px solid #E0DED8;\n" +
            "      padding: 20px 40px;\n" +
            "      text-align: center;\n" +
            "    }\n" +
            "    .footer p {\n" +
            "      font-family: Arial, sans-serif;\n" +
            "      font-size: 11px;\n" +
            "      color: #AAAAAA;\n" +
            "      margin: 0 0 4px;\n" +
            "      line-height: 1.6;\n" +
            "    }\n" +
            "    .footer p:last-child {\n" +
            "      margin: 0;\n" +
            "    }\n" +
            "  </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "  <div class=\"wrapper\">\n" +
            "    <div class=\"card\">\n" +
            "\n" +
            "      <!-- HEADER -->\n" +
            "      <div class=\"header\">\n" +
            "        <div class=\"header-logo\">\n" +
            "          <svg width=\"28\" height=\"28\" viewBox=\"0 0 28 28\" fill=\"none\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "            <circle cx=\"14\" cy=\"14\" r=\"14\" fill=\"#C8A96E\"/>\n" +
            "            <path d=\"M8 18 Q10 11 14 10 Q18 11 20 18\" stroke=\"#1A1A1A\" stroke-width=\"1.5\" fill=\"none\" stroke-linecap=\"round\"/>\n" +
            "            <circle cx=\"14\" cy=\"9\" r=\"2\" fill=\"#1A1A1A\"/>\n" +
            "            <path d=\"M10 18 Q14 15 18 18\" stroke=\"#1A1A1A\" stroke-width=\"1.2\" fill=\"none\" stroke-linecap=\"round\"/>\n" +
            "          </svg>\n" +
            "          <span>Smart Restaurant</span>\n" +
            "        </div>\n" +
            "        <p class=\"header-sub\">Đặt lại mật khẩu</p>\n" +
            "      </div>\n" +
            "\n" +
            "      <!-- BODY -->\n" +
            "      <div class=\"body\">\n" +
            "        <p class=\"greeting\">Xin chào <strong>%s</strong>,</p>\n" +
            "        <p class=\"intro\">\n" +
            "          Chúng tôi nhận được yêu cầu đặt lại mật khẩu cho tài khoản <strong>Smart Restaurant</strong> của bạn.\n" +
            "          Nhấn vào nút bên dưới để tiến hành đặt lại mật khẩu.\n" +
            "        </p>\n" +
            "\n" +
            "        <!-- RESET BOX -->\n" +
            "        <div class=\"reset-box\">\n" +
            "          <div class=\"reset-icon\">\n" +
            "            <svg width=\"40\" height=\"40\" viewBox=\"0 0 40 40\" fill=\"none\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "              <circle cx=\"20\" cy=\"20\" r=\"20\" fill=\"#1A1A1A\"/>\n" +
            "              <rect x=\"13\" y=\"19\" width=\"14\" height=\"10\" rx=\"2\" stroke=\"#C8A96E\" stroke-width=\"1.5\" fill=\"none\"/>\n" +
            "              <path d=\"M15 19v-3a5 5 0 0 1 10 0v3\" stroke=\"#C8A96E\" stroke-width=\"1.5\" fill=\"none\" stroke-linecap=\"round\"/>\n" +
            "              <circle cx=\"20\" cy=\"24\" r=\"1.5\" fill=\"#C8A96E\"/>\n" +
            "            </svg>\n" +
            "          </div>\n" +
            "          <p class=\"reset-label\">Liên kết đặt lại mật khẩu</p>\n" +
            "          <a class=\"reset-btn\" href=\"http://localhost:8080/v1/api/auth/reset-password/%s\">\n" +
            "            Đặt lại mật khẩu\n" +
            "          </a>\n" +
            "          <br/>\n" +
            "          <div class=\"reset-expiry\">\n" +
            "            <svg width=\"13\" height=\"13\" viewBox=\"0 0 13 13\" fill=\"none\">\n" +
            "              <circle cx=\"6.5\" cy=\"6.5\" r=\"5.75\" stroke=\"#854F0B\" stroke-width=\"1.2\"/>\n" +
            "              <path d=\"M6.5 3.5v3.5l2 1.5\" stroke=\"#854F0B\" stroke-width=\"1.2\" stroke-linecap=\"round\"/>\n" +
            "            </svg>\n" +
            "            <span>Liên kết hết hạn sau 15 phút</span>\n" +
            "          </div>\n" +
            "        </div>\n" +
            "\n" +
            "        <!-- FALLBACK LINK -->\n" +
            "        <div class=\"fallback\">\n" +
            "          <p>Nếu nút không hoạt động, sao chép và dán đường dẫn sau vào trình duyệt:</p>\n" +
            "          <span class=\"fallback-link\">http://localhost:8080/v1/api/auth/reset-password/%s</span>\n" +
            "        </div>\n" +
            "\n" +
            "        <!-- SECURITY NOTICE -->\n" +
            "        <div class=\"notice\">\n" +
            "          <p>\n" +
            "            Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email và liên hệ với chúng tôi\n" +
            "            ngay nếu bạn cho rằng tài khoản bị truy cập trái phép.\n" +
            "          </p>\n" +
            "        </div>\n" +
            "\n" +
            "        <p class=\"sign-off\">Trân trọng,</p>\n" +
            "        <p class=\"sign-name\">Đội ngũ Smart Restaurant</p>\n" +
            "      </div>\n" +
            "\n" +
            "      <!-- FOOTER -->\n" +
            "      <div class=\"footer\">\n" +
            "        <p>Email này được gửi tự động, vui lòng không trả lời.</p>\n" +
            "        <p>© 2026 Smart Restaurant. All rights reserved.</p>\n" +
            "      </div>\n" +
            "\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</body>\n" +
            "</html>";
}
