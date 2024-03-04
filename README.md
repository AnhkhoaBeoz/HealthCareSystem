Account for test: 
  -username:admin
  -password:123123
  -role:ROLE_ADMIN
Dự án này yêu cầu một hệ thống quản lý phòng mạch với các chức năng đa dạng cho các đối tượng khác nhau như admin, bác sĩ, y tá và bệnh nhân. Dưới đây là một mô tả tổng quan về cách triển khai các tính năng này trong một ứng dụng sử dụng Spring Boot, JWT và Spring Security:

1.Đăng nhập và phân quyền:
  Sử dụng Spring Security để xác thực và phân quyền người dùng.
  JWT (JSON Web Token) có thể được sử dụng để quản lý phiên làm việc của người dùng sau khi đăng nhập.
2.Đăng ký người dùng (bệnh nhân):

  Cho phép người dùng đăng ký tài khoản mới với vai trò là bệnh nhân.
  Yêu cầu người dùng cung cấp avatar.
  Quản lý thông tin:

3.Admin có quyền quản lý thông tin như thêm/xoá/cập nhật/tra cứu bác sĩ, y tá, và thuốc.
  Sắp lịch trực cho từng đối tượng (bác sĩ, y tá).
  Quản lý thuốc bao gồm thêm/xoá/sửa/tìm kiếm.
4.Đặt lịch khám:

  Bệnh nhân có thể đăng ký/huỷ lịch khám.
  Y tá xác nhận lịch đăng ký của bệnh nhân.
  Giới hạn số lượng lịch khám trong một ngày.
5.Ra toa thuốc:

  Bác sĩ có thể ra toa thuốc cho bệnh nhân.
  Tìm kiếm thuốc và thêm vào toa.
  Xuất đơn thuốc dạng PDF.
  Xem lịch sử khám và bệnh của bệnh nhân:

  Chỉ bác sĩ được phép xem.
  Linh hoạt theo khoảng thời gian.
6.Thống kê và báo cáo:
  Admin có thể thực hiện thống kê báo cáo số lượng bệnh nhân đến khám và doanh thu theo tháng, quý, năm.
  Sử dụng biểu đồ (ví dụ: Chart.js hoặc Google Charts) để hiển thị thông tin thống kê.

**Công nghệ sử dụng:

Sử dụng Spring Boot để phát triển ứng dụng nhanh chóng và dễ dàng.
JWT và Spring Security để xác thực và phân quyền người dùng.
MapStruct, lombok ngắn gọn khối lượng code.
