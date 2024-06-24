create database QL_BAN_GIAY_09
go

use QL_BAN_GIAY_09
go

CREATE TABLE LoaiSP (
    MaLoai CHAR(10),
    TenLoai NVARCHAR(50),
    TrangThai NVARCHAR(20), -- Thuộc tính trạng thái
    CONSTRAINT pk_LoaiSP PRIMARY KEY (MaLoai)
);
GO

CREATE TABLE NhaCC (
    MaNCC CHAR(10),
    TenNCC NVARCHAR(30),
    DiaChi NVARCHAR(30),
    Sdt VARCHAR(15),
    Email VARCHAR(20),
    TrangThai NVARCHAR(20), -- Thuộc tính trạng thái
    CONSTRAINT pk_NhaCC PRIMARY KEY (MaNCC)
);
GO
CREATE TABLE SanPham (
    MaSP CHAR(10),
    TenSP NVARCHAR(50),
    SoLuong INT,
    GiaNhap FLOAT, -- Thêm giá nhập
    GiaBan FLOAT,
    Hinh VARCHAR(50),
    MaLoai CHAR(10),
	MaNCC CHAR(10),
    Mota NVARCHAR(MAX),
    TrangThai NVARCHAR(20), -- Thuộc tính trạng thái
    CONSTRAINT pk_SanPham PRIMARY KEY (MaSP),
    CONSTRAINT fk_SanPham_LoaiSP FOREIGN KEY (MaLoai) REFERENCES LoaiSP (MaLoai),
    CONSTRAINT fk_SanPham_MaNhaCC FOREIGN KEY (MaNCC) REFERENCES NhaCC (MaNCC)
);
GO
CREATE TABLE Mau (
    MaMau CHAR(10),
    TenMau NVARCHAR(20),
    TrangThai NVARCHAR(20), -- Thuộc tính trạng thái
    CONSTRAINT pk_Mau PRIMARY KEY (MaMau)
);
GO
CREATE TABLE Size (
    MaSize CHAR(10),
    TenSize VARCHAR(20),
    SoLuong INT,
    TrangThai NVARCHAR(20), -- Thuộc tính trạng thái
    CONSTRAINT pk_Size PRIMARY KEY (MaSize)
);
GO
CREATE TABLE ChiTiet_SanPham (
    MaSP CHAR(10),
    MaMau CHAR(10),
    MaSize CHAR(10),
    SoLuong INT,
    MaCTSP CHAR(10) UNIQUE,
    TrangThai NVARCHAR(20), -- Thuộc tính trạng thái
    CONSTRAINT pk_ChiTiet_SanPham PRIMARY KEY (MaSP, MaMau, MaSize),
    CONSTRAINT fk_CTSP_SP FOREIGN KEY (MaSP) REFERENCES SanPham (MaSP),
    CONSTRAINT fk_CTSP_Mau FOREIGN KEY (MaMau) REFERENCES Mau (MaMau),
    CONSTRAINT fk_CTSP_Size FOREIGN KEY (MaSize) REFERENCES Size(MaSize)
);
GO
CREATE TABLE KhachHang (
    MaKH CHAR(10),
    TenKH NVARCHAR(30),
    DiaChi NVARCHAR(30),
    Sdt VARCHAR(15),
    Email VARCHAR(20),
    TrangThai NVARCHAR(20), -- Thuộc tính trạng thái
    CONSTRAINT pk_KhachHang PRIMARY KEY (MaKH)
);
GO
CREATE TABLE NhanVien (
    MaNV CHAR(10),
    TenNV NVARCHAR(30),
    DiaChi NVARCHAR(30),
    GioiTinh NVARCHAR(15),
    Sdt VARCHAR(15),
    Email VARCHAR(20),
    TrangThai NVARCHAR(20), -- Thuộc tính trạng thái
    CONSTRAINT pk_NhanVien PRIMARY KEY (MaNV)
);
GO

create table TaiKhoan(
	TenDN NVARCHAR(30),
	MatKhau CHAR(10),
	TrangThai NVARCHAR(20),
	CONSTRAINT pk_TaiKhoan PRIMARY KEY (TenDN)
);
GO

ALTER TABLE TaiKhoan
ADD CONSTRAINT UQ_MatKhau UNIQUE (MatKhau);
GO

INSERT INTO TaiKhoan (TenDN, MatKhau,TrangThai)
VALUES ('user1', 'password1',0),
       ('user2', 'password2',0),
       ('user3', 'password3',0);
	   GO -- 
	
DELETE FROM TaiKhoan; -- xóa toàn bộ dữ liệu trong table


CREATE TABLE HoaDon (
    MaHD CHAR(10),
    MaKH CHAR(10),
    MaNV CHAR(10),
    NgayLap DATE,
    TongTien FLOAT,
    TrangThai NVARCHAR(20), -- Thuộc tính trạng thái
    CONSTRAINT pk_HoaDon PRIMARY KEY (MaHD),
    CONSTRAINT fk_HoaDon_KhachHang FOREIGN KEY (MaKH) REFERENCES KhachHang (MaKH),
    CONSTRAINT fk_HoaDon_NhanVien FOREIGN KEY (MaNV) REFERENCES NhanVien (MaNV)
);
GO
CREATE TABLE ChiTiet_HoaDon (
    MaCTSP CHAR(10) UNIQUE,
    MaHD CHAR(10),
    SoLuong INT,
    ThanhTien FLOAT,
    TrangThai NVARCHAR(20), -- Thuộc tính trạng thái
    CONSTRAINT pk_CTHD PRIMARY KEY (MaHD, MaCTSP),
    CONSTRAINT fk_CTHD_CTSP FOREIGN KEY (MaCTSP) REFERENCES ChiTiet_SanPham (MaCTSP),
    CONSTRAINT fk_CTHD_HD FOREIGN KEY (MaHD) REFERENCES HoaDon (MaHD)
);
GO

CREATE TABLE PhieuNhap (
    MaPN CHAR(10),
    MaNCC CHAR(10),
    MaNV CHAR(10),
    NgayLap DATE,
    TongTien FLOAT,
    TrangThai NVARCHAR(20), -- Thuộc tính trạng thái
    CONSTRAINT pk_PhieuNhap PRIMARY KEY (MaPN),
    CONSTRAINT fk_PhieuNhap_NhaCC FOREIGN KEY (MaNCC) REFERENCES NhaCC (MaNCC),
    CONSTRAINT fk_PhieuNhap_NhanVien FOREIGN KEY (MaNV) REFERENCES NhanVien (MaNV)
);
GO
CREATE TABLE ChiTiet_PhieuNhap (
    MaCTSP CHAR(10) UNIQUE,
    MaPN CHAR(10),
    SoLuong INT,
    GiaNhap FLOAT, -- Thêm giá nhập
    ThanhTien FLOAT,
    TrangThai NVARCHAR(20), -- Thuộc tính trạng thái
    CONSTRAINT pk_CTPN PRIMARY KEY (MaPN, MaCTSP),
    CONSTRAINT fk_CTPN_CTSP FOREIGN KEY (MaCTSP) REFERENCES ChiTiet_SanPham (MaCTSP),
    CONSTRAINT fk_CTPN_PN FOREIGN KEY (MaPN) REFERENCES PhieuNhap (MaPN)
);
GO


-- thủ tục hiển thị danh sách khách hàng
CREATE PROC danhsachKH01 as
select * from KhachHang where TrangThai =0;
exec danhsachKH01

-- thủ tục tìm kiếm khách hàng
create proc TimKiemKH (@GiaTriCanTim nvarchar(20)) as
SELECT * FROM KhachHang WHERE (MaKH = @GiaTriCanTim OR TenKH LIKE N'%' + @GiaTriCanTim + '%') AND TrangThai = 0;
exec TimKiemKH N'kh01'

---- thủ tục chèn màu
--create proc ChenMau(@mamau varchar(10), @tenmau nvarchar(20)) as
--insert into mau values (@mamau, @tenmau,0);
--exec ChenMau 'M009',N'Cam'

--Thủ tục thêm khách hàng
CREATE PROC ThemKH(@MaKH CHAR(10), @TenKH NVARCHAR(30),@DiaChi NVARCHAR(30),@Sdt VARCHAR(15),@Email VARCHAR(20) ) as
insert into KhachHang values (@MaKH,@TenKH,@DiaChi,@Sdt,@Email,0);
exec ThemKH 'KH07','Đào Ngọc Ti', '152 Cà Mau', '0965321478','UU@gmail.com'

-- thủ tục sửa khachhang
create proc SuaKhachHang(@MaKH CHAR(10), @TenKH NVARCHAR(30),@DiaChi NVARCHAR(30),@Sdt VARCHAR(15),@Email VARCHAR(20) ) as
update KhachHang set TenKH = @TenKH,DiaChi = @DiaChi, Sdt = @Sdt, Email = @Email where MaKH = @MaKH;
exec SuaKhachHang 'KH01','Nguyễn Văn Phát','123 Tây Ninh','0254136589','HH@gmail.com'

-- thủ tục xóa khách hàng
create proc XoaKhachHang(@makh varchar(10)) as
update KhachHang set TrangThai = 1 where MaKH = @MaKH;

exec XoaKhachHang 'kh02'

select * from KhachHang

INSERT INTO KhachHang VALUES ('KH05','Nguyen Hong Anh','254 Bến Tre','0365214789','pp@gmail.com',0)



--------------------------------------------------------------------------------------------------------------------------------------------------

--Thủ tục hiện thị danh sách nhân viên
CREATE PROC danhsachnhanvien1 as
select * from NhanVien where TrangThai =0;
exec danhsachnhanvien1

-- thủ tục tìm kiếm nhân viên
create proc TimKiemNV (@GiaTriCanTim nvarchar(20)) as
SELECT * FROM NhanVien WHERE (MaNV = @GiaTriCanTim OR TenNV LIKE N'%' + @GiaTriCanTim + '%') AND TrangThai = 0;
exec TimKiemNV N'nv01'

-- thủ tục thêm nhân viên -> hoàn thành
create proc themnhanvien(@MaNV CHAR(10), @TenNV NVARCHAR(30),@DiaChi NVARCHAR(30),@GioiTinh NVARCHAR(15),@Sdt VARCHAR(15),@Email VARCHAR(20)) as
insert into NhanVien values (@MaNV, @TenNV,@DiaChi,@GioiTinh,@Sdt,@Email,0);
exec themnhanvien 'NV03',N'Cam','369 long an',N'nữ','0254136987','Wk@gmail.com'
go

-- Thủ tục kiểm tra mã nhân viên
CREATE PROCEDURE KiemTraTonTaiMaNV(@MaNV CHAR(10), @Exists BIT OUTPUT) AS
BEGIN
    IF EXISTS (SELECT 1 FROM NhanVien WHERE MaNV = @MaNV)
        SET @Exists = 1;
    ELSE
        SET @Exists = 0;
END	
go

-- thủ tục sửa nhân viên -> hoàn thành
create proc SuaNhanVien(@MaNV CHAR(10), @TenNV NVARCHAR(30),@DiaChi NVARCHAR(30),@GioiTinh NVARCHAR(15),@Sdt VARCHAR(15),@Email VARCHAR(20)) as
update NhanVien set TenNV = @TenNV,DiaChi = @DiaChi,GioiTinh =@GioiTinh,Sdt = @Sdt, Email = @Email where MaNV = @MaNV;
exec SuaNhanVien 'NV01','Nguyễn Minh Hào','258 Cần Thơ','Nam','0236514789','WW@gmail.com'

-- thủ tục xóa nhân viên -> hoàn thành
create proc XoaNhanVien(@maNV varchar(10)) as
update NhanVien set TrangThai = 1 where MaNV = @maNV;
exec XoaNhanVien 'nv03'

select * from NhanVien
--delete from NhanVien where MaNV = 'nv0022';

--------------------------------------------------------------------------------------------------------------------------------------------------
--Thủ tục hiển thị danh sách tài khoản
CREATE PROC dsTaiKhoan as
select * from TaiKhoan where TrangThai =0;
exec dsTaiKhoan

-- thủ tục tìm kiếm tài khoản
create proc TimtaiKhoan (@GiaTriCanTim nvarchar(20)) as
SELECT * FROM TaiKhoan WHERE TenDN = @GiaTriCanTim AND TrangThai = 0;
exec TimtaiKhoan N'user1'


--Thủ tục thêm tài khoản
create proc Themtaikhoan(@TenDN NVARCHAR(30),@MatKhau CHAR(10) ) as
insert into TaiKhoan values (@TenDN, @MatKhau,0);
exec Themtaikhoan 'user4',N'mk01'
go

--Thủ tục sủa tài khoản
create proc SuaTaiKhoan(@TenDN NVARCHAR(30),@MatKhau CHAR(10)) as
update TaiKhoan set MatKhau = @MatKhau where TenDN = @TenDN;
exec SuaTaiKhoan 'user1','mkuser1'


--Thủ tục xóa tài khoản
-- thủ tục xóa nhân viên -> hoàn thành
create proc XoaTaiKhoan(@TenDN varchar(10)) as
update TaiKhoan set TrangThai = 1 where TenDN = @TenDN;
exec XoaTaiKhoan 'user4'
GO


select * from TaiKhoan
---------------------------------------------------------------------------------------------
GO

CREATE PROCEDURE sinhmatk AS
BEGIN
	IF ((SELECT COUNT(*) FROM TaiKhoan) = 0)
		SELECT 'TK0000' AS TenDN;
	ELSE
		SELECT TOP 1 TenDN FROM TaiKhoan ORDER BY TenDN DESC;
END;
GO

