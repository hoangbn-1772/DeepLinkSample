# DeepLinkSample
- Tìm hiểu về Deeplink trong Android.
## Overview

<img width="1200" height="500" src="https://miro.medium.com/max/1400/1*x52IWb3LmUcK9obMS_a77Q.jpeg">

### **Deeplink**
- Là các URL đưa user trực tiếp đến nội dung cụ thể trong ứng dụng. Trong Android bạn có thể thiết lập **Deeplink** bằng cách sử dụng **intent-filter**.
- Nếu trên thiết bị người dùng có nhiều ứng dụng có thể xử lý URL, thì hệ thống sẽ hiển thị một dialog yêu cầu người dùng chọn ứng dụng để xử lý.
- Khi User click vào URL, hệ thống Android sẽ thử từng action sau theo thứ tự, cho đến khi request thành công:
  + Mở ứng dụng ưa thích của người dùng có thể xử lý URI, nếu ứng dụng được chỉ định.
  + Mở ứng dụng có sẵn có thể xử lý URI
  + Cho phép User chọn một ứng dụng từ dialog.

### **Android App link**
- Là một deeplink dựa trên website URL đã được xác minh.
- Cho phép một ứng dụng chỉ định chính nó làm trình xử lý mặc định cho liên kết. **Nhưng nó chỉ hoạt động từ Android 6.0 (API level 23) trở lên**.
- Nếu người dùng không muốn app trở thành trình xử lý mặc định, họ có thể cài đặt trong device system setting.
- Một số lợi ích:
  + An toàn và cụ thể: Android App Link sử dụng HTTP URLs liên kết đến website domain mà bạn sở hữu, vì thế không có app nào khác có thể sử dụng liên kết của bạn. Bạn phải xác minh quyền sở hữu domain.
  + Tăng trải nghiệm người dùng: sử dụng duy nhất một HTTP URL cho cùng một nội dung trên website và ứng dụng, nếu user không cài đặt ứng dụng thì có thể sử dụng website - thay vì nhận error 404.
  + Hỗ trợ Instant Apps: Có thể sử dụng app mà không cần phải cài đặt.
  + Thu hút user từ Google Search: Người dùng truy cập trực tiếp vào nội dung trong app thông qua URL từ Google trên mobile.

## Tạo Deeplink
- Để tạo Deeplink thông qua các bước sau:
1. Add **intent-filter**:
- **<action>**: Chỉ định **ACTION_VIEW** để có thể tích hợp tìm kiếm từ Google Search.
- **<category>**:
	+ **BROWSABLE** category, để intent-filter có thể truy cập được từ web browser. Nếu không có, khi click link trên browser sẽ không thể liên kết đến app.
	+ **DEFAULT** category, cho phép ứng dụng phản hồi lại các implicit intent.
- **<data>**: Có thể thêm một or nhiều thẻ <data>, mỗi thẻ đại diện cho một định dạng URI.
	+ Thuộc tính **android:scheme** là bắt buộc. Nếu không có, tất cả các thuộc tính khác của URI sẽ bị bỏ qua.
	+ **host**: Là domain URI, chỉ có ý nghĩa khi thuộc tính **scheme** được cấu hình. Để khớp nhiều subdomain, sử dụng (*) để tạo ra 0 hoặc nhiều host. Ví dụ ***.google.com** có thể tạo ra các host như www.google.com, .google.com, developer.google.com
	+ **port**: là cổng của URI. Chỉ có giá trị khi **scheme và host** được cấu hình.
	+ **path, pathPrefix, pathPattern**: Phải bắt đầu bằng **'/'**
		+ path: Là đường dẫn hoàn chỉnh khớp với đường dẫn hoàn chỉnh trong Intent.
		+ pathPrefix: Chỉ định đường dẫn một phần khớp với phần đầu của đường dẫn trong Intent.
		+ pathPattern: Tương tự như path nhưng nó có thể chứa các ký tự đại diện như: (*), (.*)

2. Lấy data từ Intent gửi đến.
- Dữ liệu gửi đến là một URI, có thể lấy được các params cần thiết bằng các method như **getData()**, **getAction()**

3. Test deep link
- Bạn có thể sử dụng <a href="https://developer.android.com/studio/command-line/adb">Android Debug Bridge</a> cùng với **activity manager (am)** để kiểm tra intent filter URI. Bạn có thể run lệnh trong command với device or emulator.
- Cú pháp chung: **$ adb shell am start -W -a android.intent.action.VIEW -d <URI> <PACKAGE>**
- Ví dụ: **adb shell am start -W -a android.intent.action.VIEW -d "example://gizmos" com.example.deeplinksample**
