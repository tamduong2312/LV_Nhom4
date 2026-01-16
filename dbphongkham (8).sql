-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 16, 2026 at 09:35 AM
-- Server version: 8.2.0
-- PHP Version: 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbphongkham`
--

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `sp_ThemNhanVienVaTaiKhoan`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ThemNhanVienVaTaiKhoan` (IN `p_HoTen` VARCHAR(100), IN `p_GioiTinh` BIT(2), IN `p_NgaySinh` DATE, IN `p_CCCD` VARCHAR(20), IN `p_DiaChi` VARCHAR(100), IN `p_SDT` VARCHAR(20), IN `p_Email` VARCHAR(100), IN `p_BangCap` VARCHAR(100), IN `p_ChucVu` VARCHAR(50), IN `p_ChuyenKhoa` VARCHAR(50), IN `p_NgayVaoLam` DATE, IN `p_Username` VARCHAR(100), IN `p_Password` VARCHAR(100), IN `p_Role` VARCHAR(50))   BEGIN

    DECLARE v_MaNV INT;

   
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

  
    START TRANSACTION;

   
    INSERT INTO nhan_vien (ho_ten, gioi_tinh, ngay_sinh, cccd, dia_chi, so_dien_thoai, email, bang_cap, chuc_vu, chuyen_khoa, ngay_vao_lam)
    VALUES (p_HoTen, p_GioiTinh, p_NgaySinh, p_CCCD, p_DiaChi, p_SDT, p_Email, p_BangCap, p_ChucVu, p_ChuyenKhoa, p_NgayVaoLam);

  
    SET v_MaNV = LAST_INSERT_ID();


    INSERT INTO tai_khoan (username, email, mat_khau, vai_tro, ma_nhan_vien, lan_dau_dang_nhap)
    VALUES (p_Username, p_Email, p_Password, p_Role, v_MaNV, 1);


    COMMIT;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `bang_phan_cong_ca_lam`
--

DROP TABLE IF EXISTS `bang_phan_cong_ca_lam`;
CREATE TABLE IF NOT EXISTS `bang_phan_cong_ca_lam` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_nhan_vien` int NOT NULL,
  `phong` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gio_lam` time DEFAULT NULL,
  `gio_ket_thuc` time DEFAULT NULL,
  `thu` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_phancong_nhanvien` (`ma_nhan_vien`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `bang_phan_cong_ca_lam`
--

INSERT INTO `bang_phan_cong_ca_lam` (`id`, `ma_nhan_vien`, `phong`, `gio_lam`, `gio_ket_thuc`, `thu`) VALUES
(1, 214, 'P. Khám Nội Tổng Quát', '08:00:00', '17:00:00', 'Thứ 6'),
(2, 214, 'P. Khám Nội Tổng Quát', '08:00:00', '17:00:00', 'Thứ 4'),
(3, 214, 'P. Khám Nội Tổng Quát', '08:00:00', '17:00:00', 'Thứ 2'),
(4, 215, 'P. Khám Nội Tổng Quát', '08:00:00', '17:00:00', 'Thứ 7'),
(5, 215, 'P. Khám Nội Tổng Quát', '08:00:00', '17:00:00', 'Thứ 5'),
(6, 215, 'P. Khám Nội Tổng Quát', '08:00:00', '17:00:00', 'Thứ 3'),
(7, 216, 'P. Khám Nhi Khoa', '08:00:00', '17:00:00', 'Thứ 6'),
(8, 216, 'P. Khám Nhi Khoa', '08:00:00', '17:00:00', 'Thứ 4'),
(9, 216, 'P. Khám Nhi Khoa', '08:00:00', '17:00:00', 'Thứ 2'),
(10, 217, 'P. Khám Nhi Khoa', '08:00:00', '17:00:00', 'Thứ 7'),
(11, 217, 'P. Khám Nhi Khoa', '08:00:00', '17:00:00', 'Thứ 5'),
(12, 217, 'P. Khám Nhi Khoa', '08:00:00', '17:00:00', 'Thứ 3'),
(13, 218, 'P.Tai - Mũi - Họng', '08:00:00', '17:00:00', 'Thứ 6'),
(14, 218, 'P.Tai - Mũi - Họng', '08:00:00', '17:00:00', 'Thứ 4'),
(15, 218, 'P.Tai - Mũi - Họng', '08:00:00', '17:00:00', 'Thứ 2'),
(16, 219, 'P.Tai - Mũi - Họng', '08:00:00', '17:00:00', 'Thứ 7'),
(17, 219, 'P.Tai - Mũi - Họng', '08:00:00', '17:00:00', 'Thứ 5'),
(18, 219, 'P.Tai - Mũi - Họng', '08:00:00', '17:00:00', 'Thứ 3'),
(22, 240, 'Quầy thu ngân', '08:00:00', '17:00:00', 'Thứ 6'),
(23, 240, 'Quầy thu ngân', '08:00:00', '17:00:00', 'Thứ 4'),
(24, 240, 'Quầy thu ngân', '08:00:00', '17:00:00', 'Thứ 2'),
(26, 241, 'Quầy thu ngân', '08:00:00', '17:00:00', 'Thứ 7'),
(27, 241, 'Quầy thu ngân', '08:00:00', '17:00:00', 'Thứ 5'),
(28, 241, 'Quầy thu ngân', '08:00:00', '17:00:00', 'Thứ 3'),
(29, 228, 'P. Khám Nhi Khoa', '08:00:00', '17:00:00', 'Thứ 6'),
(30, 228, 'P. Khám Nhi Khoa', '08:00:00', '17:00:00', 'Thứ 4'),
(31, 228, 'P. Khám Nhi Khoa', '08:00:00', '17:00:00', 'Thứ 2'),
(36, 229, 'P. Khám Nhi Khoa', '08:00:00', '17:00:00', 'Thứ 7'),
(37, 229, 'P. Khám Nhi Khoa', '08:00:00', '17:00:00', 'Thứ 5'),
(38, 229, 'P. Khám Nhi Khoa', '08:00:00', '17:00:00', 'Thứ 3'),
(39, 238, 'Quầy Tiếp Đón & Lễ Tân', '07:30:00', '16:30:00', 'Thứ 6'),
(40, 238, 'Quầy Tiếp Đón & Lễ Tân', '07:30:00', '16:30:00', 'Thứ 4'),
(41, 238, 'Quầy Tiếp Đón & Lễ Tân', '07:30:00', '16:30:00', 'Thứ 2'),
(42, 239, 'Quầy Tiếp Đón & Lễ Tân', '07:30:00', '16:30:00', 'Thứ 7'),
(43, 239, 'Quầy Tiếp Đón & Lễ Tân', '07:30:00', '16:30:00', 'Thứ 5'),
(44, 239, 'Quầy Tiếp Đón & Lễ Tân', '07:30:00', '16:30:00', 'Thứ 3'),
(45, 242, 'Quầy Thuốc', '08:00:00', '17:00:00', 'Thứ 6'),
(46, 242, 'Quầy Thuốc', '08:00:00', '17:00:00', 'Thứ 4'),
(47, 242, 'Quầy Thuốc', '08:00:00', '17:00:00', 'Thứ 2'),
(48, 243, 'Quầy Thuốc', '08:00:00', '17:00:00', 'Thứ 7'),
(49, 243, 'Quầy Thuốc', '08:00:00', '17:00:00', 'Thứ 5'),
(50, 243, 'Quầy Thuốc', '08:00:00', '17:00:00', 'Thứ 3'),
(54, 247, 'P. Xét nghiệm', '08:00:00', '17:00:00', 'Thứ 7'),
(55, 247, 'P. Xét nghiệm', '08:00:00', '17:00:00', 'Thứ 5'),
(57, 226, 'P. Chẩn Đoán Hình ảnh', '08:00:00', '17:00:00', 'Thứ 2'),
(58, 227, 'P. Chẩn Đoán Hình ảnh', '08:00:00', '16:00:00', 'Thứ 3'),
(59, 226, 'P. Chẩn Đoán Hình ảnh', '07:00:00', '16:00:00', 'Thứ 4'),
(60, 227, 'P. Chẩn Đoán Hình ảnh', '08:00:00', '17:00:00', 'Thứ 5'),
(61, 226, 'P. Chẩn Đoán Hình ảnh', '08:00:00', '17:00:00', 'Thứ 6'),
(62, 227, 'P. Chẩn Đoán Hình ảnh', '08:00:00', '17:00:00', 'Thứ 7'),
(63, 220, 'P. Răng - Hàm - Mặt', '08:00:00', '17:00:00', 'Thứ 2'),
(64, 221, 'P. Răng - Hàm - Mặt', '08:00:00', '17:00:00', 'Thứ 3'),
(65, 220, 'P. Răng - Hàm - Mặt', '08:00:00', '17:00:00', 'Thứ 4'),
(66, 221, 'P. Răng - Hàm - Mặt', '08:00:00', '17:00:00', 'Thứ 5'),
(67, 220, 'P. Răng - Hàm - Mặt', '08:00:00', '17:00:00', 'Thứ 6'),
(68, 221, 'P. Răng - Hàm - Mặt', '08:00:00', '17:00:00', 'Thứ 7'),
(69, 224, 'P. Tim mạch', '08:00:00', '17:00:00', 'Thứ 2'),
(70, 225, 'P. Tim mạch', '08:00:00', '17:00:00', 'Thứ 3'),
(71, 224, 'P. Tim mạch', '08:00:00', '17:00:00', 'Thứ 4'),
(72, 225, 'P. Tim mạch', '07:00:00', '17:00:00', 'Thứ 5'),
(73, 224, 'P. Tim mạch', '08:00:00', '17:00:00', 'Thứ 6'),
(74, 225, 'P. Tim mạch', '08:00:00', '17:00:00', 'Thứ 7'),
(75, 222, 'P. Xét nghiệm', '08:00:00', '17:00:00', 'Thứ 2'),
(76, 223, 'P. Xét nghiệm', '08:00:00', '17:00:00', 'Thứ 3'),
(77, 222, 'P. Xét nghiệm', '08:00:00', '17:00:00', 'Thứ 4'),
(78, 223, 'P. Xét nghiệm', '08:00:00', '17:00:00', 'Thứ 5'),
(79, 222, 'P. Xét nghiệm', '08:00:00', '17:00:00', 'Thứ 6'),
(80, 223, 'P. Xét nghiệm', '08:00:00', '17:00:00', 'Thứ 7'),
(81, 248, 'P. Chẩn Đoán Hình ảnh', '08:00:00', '17:00:00', 'Thứ 2'),
(82, 249, 'P. Chẩn Đoán Hình ảnh', '08:00:00', '17:00:00', 'Thứ 3'),
(83, 248, 'P. Chẩn Đoán Hình ảnh', '08:00:00', '17:00:00', 'Thứ 4'),
(84, 249, 'P. Chẩn Đoán Hình ảnh', '08:00:00', '17:00:00', 'Thứ 5'),
(85, 248, 'P. Chẩn Đoán Hình ảnh', '08:00:00', '17:00:00', 'Thứ 6'),
(86, 249, 'P. Chẩn Đoán Hình ảnh', '08:00:00', '17:00:00', 'Thứ 7'),
(87, 251, 'P. Xét nghiệm', '08:00:00', '17:00:00', 'Thứ 2'),
(88, 247, 'P. Xét nghiệm', '08:00:00', '17:00:00', 'Thứ 3'),
(89, 251, 'P. Xét nghiệm', '08:00:00', '17:00:00', 'Thứ 4'),
(90, 251, 'P. Xét nghiệm', '08:00:00', '17:00:00', 'Thứ 6'),
(91, 232, 'P. Răng - Hàm - Mặt', '08:00:00', '17:00:00', 'Thứ 2'),
(92, 233, 'P. Răng - Hàm - Mặt', '08:00:00', '17:00:00', 'Thứ 3'),
(93, 232, 'P. Răng - Hàm - Mặt', '08:00:00', '17:00:00', 'Thứ 4'),
(95, 233, 'P. Răng - Hàm - Mặt', '08:00:00', '17:00:00', 'Thứ 5'),
(96, 232, 'P. Răng - Hàm - Mặt', '08:00:00', '17:00:00', 'Thứ 6'),
(97, 233, 'P. Răng - Hàm - Mặt', '08:00:00', '17:00:00', 'Thứ 7'),
(98, 230, 'P.Tai - Mũi - Họng', '08:00:00', '17:00:00', 'Thứ 2'),
(99, 231, 'P.Tai - Mũi - Họng', '08:00:00', '17:00:00', 'Thứ 3'),
(100, 230, 'P.Tai - Mũi - Họng', '08:00:00', '17:00:00', 'Thứ 4'),
(101, 231, 'P.Tai - Mũi - Họng', '08:00:00', '17:00:00', 'Thứ 5'),
(102, 230, 'P.Tai - Mũi - Họng', '08:00:00', '17:00:00', 'Thứ 6'),
(103, 231, 'P.Tai - Mũi - Họng', '08:00:00', '17:00:00', 'Thứ 7'),
(104, 234, 'P. Tim mạch', '08:00:00', '17:00:00', 'Thứ 2'),
(105, 235, 'P. Tim mạch', '08:00:00', '17:00:00', 'Thứ 3'),
(106, 234, 'P. Tim mạch', '08:00:00', '17:00:00', 'Thứ 4'),
(107, 235, 'P. Tim mạch', '08:00:00', '17:00:00', 'Thứ 5'),
(108, 234, 'P. Tim mạch', '08:00:00', '17:00:00', 'Thứ 6'),
(109, 235, 'P. Tim mạch', '08:00:00', '17:00:00', 'Thứ 7'),
(110, 236, 'P. Khám Nội Tổng Quát', '08:00:00', '17:00:00', 'Thứ 2'),
(111, 237, 'P. Khám Nội Tổng Quát', '08:00:00', '17:00:00', 'Thứ 3'),
(112, 236, 'P. Khám Nội Tổng Quát', '08:00:00', '17:00:00', 'Thứ 4'),
(113, 237, 'P. Khám Nội Tổng Quát', '08:00:00', '17:00:00', 'Thứ 5'),
(114, 236, 'P. Khám Nội Tổng Quát', '08:00:00', '17:00:00', 'Thứ 6'),
(115, 237, 'P. Khám Nội Tổng Quát', '08:00:00', '17:00:00', 'Thứ 7');

-- --------------------------------------------------------

--
-- Table structure for table `benh_nhan`
--

DROP TABLE IF EXISTS `benh_nhan`;
CREATE TABLE IF NOT EXISTS `benh_nhan` (
  `ma_benh_nhan` int NOT NULL AUTO_INCREMENT,
  `ho_ten` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `so_dien_thoai` varchar(20) DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `nghe_nghiep` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `nhom_mau` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `di_ung_thuoc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `nguoi_giam_ho` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `so_dien_thoai_nguoi_giam_ho` varchar(20) DEFAULT NULL,
  `ghi_chu` text,
  `cccd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gioi_tinh` bit(2) NOT NULL,
  PRIMARY KEY (`ma_benh_nhan`),
  UNIQUE KEY `UNQ_cccd` (`cccd`),
  UNIQUE KEY `UNQ_sdt` (`so_dien_thoai`),
  UNIQUE KEY `UNQ_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `benh_nhan`
--

INSERT INTO `benh_nhan` (`ma_benh_nhan`, `ho_ten`, `ngay_sinh`, `dia_chi`, `so_dien_thoai`, `email`, `nghe_nghiep`, `nhom_mau`, `di_ung_thuoc`, `nguoi_giam_ho`, `so_dien_thoai_nguoi_giam_ho`, `ghi_chu`, `cccd`, `gioi_tinh`) VALUES
(1, 'khánh vy', '2000-05-02', '45 Lê Lợi, Q1, TP.HCM', '0909883211', NULL, 'Giáo viên', 'B', '123123', '', '123456789', '', '123456781123', b'01'),
(2, 'Nguyễn Thị Hồng', '1988-07-20', '78 Cách Mạng Tháng 8, TP.HCM', '0909555666', 'hongnt@gmail.com', 'Giáo viên', 'A', 'Penicillin', NULL, NULL, NULL, '123213213', b'00'),
(3, 'Lê Gia Bảo', '2000-05-02', '45 Lê Lợi, Q1, TP.HCM', '0909888777', NULL, 'Học sinh', 'B', '123123', '21312', '0', '23123', '909888889', b'00'),
(12, 'hao hao', '2000-05-06', '45 Lê Lợi, Q1, TP.HCM', '0909881257', NULL, 'Giáo viên', 'B', '123123', '', '123456789', '', '123456784789', b'01'),
(22, 'thảo vy', '2000-05-06', '45 Lê Lợi, Q1, TP.HCM', '0909881236', NULL, 'Giáo viên', 'B', '123123', '', '123456789', '', '123456781234', b'01'),
(26, 'khánh nhi', '2000-05-02', '45 Lê Lợi, Q1, TP.HCM', '0909881121', 'tamduonge7@gmail.com', 'Giáo viên', 'B', '123123', '', '123456789', '', '123456782341', b'01'),
(30, 'hoàng lan', '2000-05-02', '45 Lê Lợi, Q1, TP.HCM', '0909883366', NULL, 'Giáo viên', 'B', '123123', '', '0', '', '123456789876', b'00'),
(31, 'Phạm nhật minh', '2000-05-02', '45 Lê Lợi, Q1, TP.HCM', '0909888876', NULL, 'Giáo viên', 'B', '123123', '', '0', '', '123456780012', b'00'),
(37, 'Phạm nhật minh thư', '2000-05-02', '45 Lê Lợi, Q1, TP.HCM', '0123456789', NULL, 'Giáo viên', 'B', '', '', '0', '', '123456780013', b'00'),
(40, 'Phạm nhật Ánh', '2000-05-02', '45 Lê Lợi, Q1, TP.HCM', '0123456781', 'tamduonge71@gmail.com', 'Giáo viên', 'B', '', '', '0', '', '123456780017', b'00');

-- --------------------------------------------------------

--
-- Table structure for table `chi_so_kham_tong_hop`
--

DROP TABLE IF EXISTS `chi_so_kham_tong_hop`;
CREATE TABLE IF NOT EXISTS `chi_so_kham_tong_hop` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_phieu_kham` int NOT NULL,
  `ma_nhan_vien_nhap` int DEFAULT NULL,
  `nhiet_do` float DEFAULT NULL,
  `nhip_tim` int DEFAULT NULL,
  `nhip_tho` int DEFAULT NULL,
  `huyet_ap_tam_thu` int DEFAULT NULL,
  `huyet_ap_tam_truong` int DEFAULT NULL,
  `can_nang` float DEFAULT NULL,
  `chieu_cao` float DEFAULT NULL,
  `spo2` float DEFAULT NULL,
  `vong_dau` float DEFAULT NULL,
  `tinh_trang_dinh_duong` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tam_ly_hanh_vi` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `kham_tai_mui_hong_nhi` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `kham_ho_hap_nhi` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `kham_da_niem_mac_nhi` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `co_quan_khac_nhi` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `tinh_trang_rang` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `sau_rang` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cao_rang` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `viem_nuou` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `khop_can` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `niem_mac_mieng` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `do_lung_lay` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phu_hinh_cu` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `benh_ly_khac_rhm` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `thinh_luc_tai_trai` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `thinh_luc_tai_phai` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tinh_trang_mui` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tinh_trang_hong` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `soi_tai_mui_hong` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `ong_tai` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `mang_nhi_phai` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mang_nhi_trai` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `vach_ngan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cuon_mui` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `khe_mui` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `amidan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `thanh_quan` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `cholesterol` float DEFAULT NULL,
  `hdl_cholesterol` float DEFAULT NULL,
  `ldl_cholesterol` float DEFAULT NULL,
  `triglyceride` float DEFAULT NULL,
  `duong_huyet` float DEFAULT NULL,
  `ecg_ket_qua` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `sieu_am_tim` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `ghi_chu` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `ngay_tao` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_phieukham_tonghop` (`ma_phieu_kham`),
  KEY `FK_MaNVnhap` (`ma_nhan_vien_nhap`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `chi_tiet_chi_dinh`
--

DROP TABLE IF EXISTS `chi_tiet_chi_dinh`;
CREATE TABLE IF NOT EXISTS `chi_tiet_chi_dinh` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_phieu_chi_dinh` int NOT NULL,
  `ma_dich_vu` int NOT NULL,
  `so_luong` int DEFAULT '1',
  `don_gia` decimal(10,2) NOT NULL,
  `trang_thai_dv` varchar(20) DEFAULT 'CHUA_THUC_HIEN',
  `ma_nhan_vien_thuc_hien` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk-maphieuchidinh` (`ma_phieu_chi_dinh`),
  KEY `fk-madv` (`ma_dich_vu`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `chuc_vu`
--

DROP TABLE IF EXISTS `chuc_vu`;
CREATE TABLE IF NOT EXISTS `chuc_vu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_chuc_vu` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ten_chuc_vu` (`ten_chuc_vu`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `chuc_vu`
--

INSERT INTO `chuc_vu` (`id`, `ten_chuc_vu`) VALUES
(16, 'Bác sĩ'),
(5, 'Dược sĩ'),
(15, 'Kỹ thuật viên Chẩn đoán hình ảnh'),
(13, 'Kỹ thuật viên Xét nghiệm'),
(3, 'Lễ tân'),
(6, 'Nhân viên kho'),
(9, 'Quản trị viên'),
(4, 'Thu ngân'),
(7, 'Trợ lý bác sĩ chuyên khoa'),
(8, 'Trợ lý bác sĩ tổng quát');

-- --------------------------------------------------------

--
-- Table structure for table `chuyen_khoa`
--

DROP TABLE IF EXISTS `chuyen_khoa`;
CREATE TABLE IF NOT EXISTS `chuyen_khoa` (
  `ma_chuyen_khoa` int NOT NULL AUTO_INCREMENT,
  `ten_chuyen_khoa` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mo_ta` text,
  `so_luong_toi_da` int DEFAULT NULL,
  PRIMARY KEY (`ma_chuyen_khoa`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `chuyen_khoa`
--

INSERT INTO `chuyen_khoa` (`ma_chuyen_khoa`, `ten_chuyen_khoa`, `mo_ta`, `so_luong_toi_da`) VALUES
(1, 'Nội tổng quát', 'Khám và điều trị các bệnh nội khoa thông thường', 45),
(3, 'Nhi khoa', 'Khám và điều trị bệnh cho trẻ em', NULL),
(4, 'Tai - Mũi - Họng', 'Khám và điều trị bệnh lý tai, mũi, họng', NULL),
(5, 'Răng - Hàm - Mặt', 'Khám, nhổ, trám và phục hình răng', NULL),
(7, 'Xét nghiệm', 'Thực hiện các xét nghiệm máu, nước tiểu, sinh hóa...', NULL),
(11, 'Tim mạch', 'kiểm tra về tim', NULL),
(12, 'Chẩn đoán hình ảnh', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ct_hoa_don`
--

DROP TABLE IF EXISTS `ct_hoa_don`;
CREATE TABLE IF NOT EXISTS `ct_hoa_don` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_hoa_don` int NOT NULL,
  `noi_dung` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `loai_muc` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id_goc` int DEFAULT NULL,
  `so_luong` int DEFAULT '1',
  `don_gia` decimal(15,2) DEFAULT '0.00',
  `thanh_tien` decimal(15,2) DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `FK_mahoadoan` (`ma_hoa_don`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ct_phieu_nhap_thuoc`
--

DROP TABLE IF EXISTS `ct_phieu_nhap_thuoc`;
CREATE TABLE IF NOT EXISTS `ct_phieu_nhap_thuoc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_phieu_nhap_thuoc` int NOT NULL,
  `ma_thuoc` int NOT NULL,
  `so_luong_nhap` int NOT NULL,
  `don_gia_nhap` decimal(18,2) NOT NULL,
  `thanh_tien` decimal(18,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ma_phieu_nhap_thuoc` (`ma_phieu_nhap_thuoc`),
  KEY `ma_thuoc` (`ma_thuoc`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `ct_phieu_nhap_thuoc`
--

INSERT INTO `ct_phieu_nhap_thuoc` (`id`, `ma_phieu_nhap_thuoc`, `ma_thuoc`, `so_luong_nhap`, `don_gia_nhap`, `thanh_tien`) VALUES
(6, 6, 1, 5, 10000.00, 50000.00),
(7, 7, 1, 1000, 50000.00, 50000000.00),
(8, 7, 1, 1000, 10000.00, 10000000.00),
(9, 8, 3, 5, 5000.00, 25000.00),
(10, 9, 2, 5, 1000.00, 5000.00),
(12, 11, 3, 5, 1000.00, 5000.00),
(13, 12, 1, 500, 1000.00, 500000.00),
(14, 13, 1, 10, 5000.00, 50000.00),
(15, 14, 1, 10, 5000.00, 50000.00),
(16, 15, 3, 100, 20000.00, 2000000.00),
(17, 16, 3, 1, 200.00, 200.00),
(18, 17, 1, 10, 100.00, 1000.00),
(21, 19, 2, 12, 1.00, 12.00),
(24, 20, 5, 50, 20000.00, 1000000.00),
(25, 20, 6, 20, 40000.00, 800000.00),
(26, 20, 7, 200, 3000.00, 600000.00),
(27, 20, 8, 10, 65000.00, 650000.00),
(28, 20, 9, 500, 4500.00, 2250000.00),
(29, 20, 10, 100, 6000.00, 600000.00),
(30, 20, 11, 5, 300000.00, 1500000.00),
(31, 20, 12, 60, 10000.00, 600000.00),
(32, 20, 13, 10, 70000.00, 700000.00),
(33, 22, 2, 11, 11000.00, 121000.00);

-- --------------------------------------------------------

--
-- Table structure for table `ct_toa_thuoc`
--

DROP TABLE IF EXISTS `ct_toa_thuoc`;
CREATE TABLE IF NOT EXISTS `ct_toa_thuoc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_toa_thuoc` int NOT NULL,
  `ma_thuoc` int NOT NULL,
  `lieu_dung` varchar(100) DEFAULT NULL COMMENT 'VD: 3 lần/ngày',
  `sang` varchar(50) DEFAULT NULL COMMENT 'Sáng',
  `trua` varchar(50) DEFAULT NULL COMMENT 'Trưa',
  `chieu` varchar(50) DEFAULT NULL COMMENT 'Chiều',
  `toi` varchar(50) DEFAULT NULL COMMENT 'Tối',
  `so_ngay` int NOT NULL,
  `cach_dung` text COMMENT 'Ghi chú/Lời dặn chi tiết của bác sĩ',
  `thoi_diem_dung` varchar(100) DEFAULT NULL COMMENT 'VD: Sau ăn, Trước ngủ',
  PRIMARY KEY (`id`),
  KEY `FK_ToaThuoc_CTToaThuoc` (`ma_toa_thuoc`),
  KEY `FK_CTToaThuoc_MaThuoc` (`ma_thuoc`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `dang_ky_kham_benh`
--

DROP TABLE IF EXISTS `dang_ky_kham_benh`;
CREATE TABLE IF NOT EXISTS `dang_ky_kham_benh` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_benh_nhan` int NOT NULL,
  `ma_nhan_vien` int DEFAULT NULL,
  `ma_chuyen_khoa` int NOT NULL,
  `so_thu_tu` int NOT NULL,
  `thoi_gian_dang_ky` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `trang_thai` enum('DA_THUC_HIEN_CLS','CHI_DINH_CLS','CHO_CLS','CHO_KHAM','DANG_KHAM','VANG_MAT','CHO_KHAM_BS','DA_KHAM','HUY','DOI_CHUYEN_KHOA') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'CHO_KHAM',
  `ghi_chu` varchar(255) DEFAULT NULL,
  `ma_phieu_kham` int DEFAULT NULL,
  `ma_chi_tiet_chi_dinh` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dkbn_benhnhan` (`ma_benh_nhan`),
  KEY `fk_dkbn_nhanvien` (`ma_nhan_vien`),
  KEY `fk_dkbn_chuyenkhoa` (`ma_chuyen_khoa`),
  KEY `fk_dk_phieukham` (`ma_phieu_kham`),
  KEY `fk_dk_mactcd` (`ma_chi_tiet_chi_dinh`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `danh_muc_benh_ly`
--

DROP TABLE IF EXISTS `danh_muc_benh_ly`;
CREATE TABLE IF NOT EXISTS `danh_muc_benh_ly` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_icd` varchar(20) NOT NULL,
  `ten_benh` varchar(255) NOT NULL,
  `trieu_chung_goi_y` text,
  `chuyen_khoa_lien_quan` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ma_icd` (`ma_icd`),
  UNIQUE KEY `ten_benh` (`ten_benh`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `danh_muc_benh_ly`
--

INSERT INTO `danh_muc_benh_ly` (`id`, `ma_icd`, `ten_benh`, `trieu_chung_goi_y`, `chuyen_khoa_lien_quan`) VALUES
(1, 'J00', 'Viêm mũi họng cấp (Cảm lạnh)', 'Sổ mũi, hắt hơi, đau họng, sốt nhẹ', 'Tai - Mũi - Họng'),
(2, 'J02', 'Viêm họng cấp', 'Đau họng, nuốt đau, sốt, sưng hạch', 'Tai - Mũi - Họng'),
(3, 'J03', 'Viêm amidan cấp', 'Amidan sưng đỏ, có mủ, sốt cao, hơi thở hôi', 'Tai - Mũi - Họng'),
(4, 'J20', 'Viêm phế quản cấp', 'Ho khan hoặc có đờm, khò khè, khó thở nhẹ', 'Nội tổng quát'),
(5, 'J30', 'Viêm mũi vận mạch (Dị ứng)', 'Hắt hơi liên tục, ngứa mũi, chảy nước mũi trong', 'Tai - Mũi - Họng'),
(6, 'H66', 'Viêm tai giữa mủ', 'Đau tai, chảy mủ tai, giảm thính lực', 'Tai - Mũi - Họng'),
(7, 'K29', 'Viêm dạ dày và tá tràng', 'Đau thượng vị, buồn nôn, ợ chua', 'Nội tổng quát'),
(8, 'K21', 'Bệnh trào ngược dạ dày thực quản (GERD)', 'Ợ nóng, nóng rát sau xương ức, trào ngược', 'Nội tổng quát'),
(9, 'A09', 'Viêm dạ dày ruột nhiễm trùng (Tiêu chảy)', 'Tiêu chảy, nôn mửa, đau bụng, sốt', 'Nội tổng quát'),
(10, 'K59.0', 'Táo bón', 'Đại tiện khó, ít, phân cứng', 'Nội tổng quát'),
(11, 'I10', 'Tăng huyết áp vô căn', 'Đau đầu, chóng mặt, hồi hộp, huyết áp > 140/90', 'Tim mạch'),
(12, 'E11', 'Đái tháo đường type 2', 'Khát nước, tiểu nhiều, sụt cân, mệt mỏi', 'Nội tổng quát'),
(13, 'E78', 'Rối loạn chuyển hóa Lipoprotein (Mỡ máu)', 'Thường không triệu chứng, phát hiện qua xét nghiệm', 'Nội tổng quát'),
(14, 'I20', 'Cơn đau thắt ngực', 'Đau thắt ngực trái, lan ra tay trái, khó thở', 'Tim mạch'),
(15, 'M54.5', 'Đau lưng vùng thắt lưng', 'Đau ê ẩm hoặc nhói vùng thắt lưng, hạn chế vận động', 'Nội tổng quát'),
(16, 'M17', 'Thoái hóa khớp gối', 'Đau đầu gối khi đi lại, lục cục khớp, cứng khớp sáng sớm', 'Nội tổng quát'),
(17, 'M79.1', 'Đau cơ (Myalgia)', 'Đau nhức cơ bắp toàn thân hoặc khu trú', 'Nội tổng quát'),
(18, 'K02', 'Sâu răng', 'Đau nhức răng, lỗ sâu trên mặt răng, ê buốt', 'Răng - Hàm - Mặt'),
(19, 'K05', 'Viêm nướu (Lợi)', 'Lợi sưng đỏ, chảy máu khi đánh răng', 'Răng - Hàm - Mặt'),
(20, 'K04', 'Viêm tủy răng', 'Đau dữ dội từng cơn, đau lan lên đầu', 'Răng - Hàm - Mặt'),
(21, 'K08.8', 'Đau răng (chưa rõ nguyên nhân)', 'Đau vùng răng hàm', 'Răng - Hàm - Mặt'),
(22, 'B08.4', 'Bệnh tay chân miệng', 'Loét miệng, phỏng nước lòng bàn tay chân, sốt', 'Nhi khoa'),
(23, 'A90', 'Sốt xuất huyết Dengue', 'Sốt cao đột ngột, đau đầu, đau hốc mắt, phát ban', 'Nhi khoa'),
(24, 'L20', 'Viêm da cơ địa (Chàm)', 'Ngứa, da đỏ, khô, bong tróc', 'Nhi khoa');

-- --------------------------------------------------------

--
-- Table structure for table `dich_vu`
--

DROP TABLE IF EXISTS `dich_vu`;
CREATE TABLE IF NOT EXISTS `dich_vu` (
  `ma_dich_vu` int NOT NULL AUTO_INCREMENT,
  `ten_dich_vu` varchar(50) NOT NULL,
  `don_gia` decimal(10,2) NOT NULL,
  `loai_dich_vu` varchar(50) DEFAULT NULL,
  `phong` int NOT NULL,
  `ma_chuyen_khoa` int DEFAULT NULL,
  PRIMARY KEY (`ma_dich_vu`),
  KEY `FK_PHONG_DV` (`phong`),
  KEY `FK_CK_CK` (`ma_chuyen_khoa`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `dich_vu`
--

INSERT INTO `dich_vu` (`ma_dich_vu`, `ten_dich_vu`, `don_gia`, `loai_dich_vu`, `phong`, `ma_chuyen_khoa`) VALUES
(1, 'Khám Nội tổng quát', 100000.00, 'KHAM_BENH', 1, 1),
(2, 'Khám Nhi khoa', 100000.00, 'KHAM_BENH', 5, 3),
(3, 'Khám Tai Mũi Họng', 120000.00, 'KHAM_BENH', 6, 4),
(4, 'Khám Răng Hàm Mặt', 120000.00, 'KHAM_BENH', 7, 5),
(5, 'Khám Tim mạch', 150000.00, 'KHAM_BENH', 8, 11),
(7, 'Tổng phân tích tế bào máu (CBC)', 80000.00, 'CLS_XET_NGHIEM', 4, 7),
(8, 'Định lượng Glucose (Đường huyết)', 40000.00, 'CLS_XET_NGHIEM', 4, 7),
(9, 'Bộ mỡ máu (Cholesterol, Triglyceride...)', 150000.00, 'CLS_XET_NGHIEM', 4, 7),
(10, 'Chức năng Gan (AST, ALT, GGT)', 120000.00, 'CLS_XET_NGHIEM', 4, 7),
(11, 'Chức năng Thận (Ure, Creatinine)', 80000.00, 'CLS_XET_NGHIEM', 4, 7),
(12, 'Tổng phân tích nước tiểu', 50000.00, 'CLS_XET_NGHIEM', 4, 7),
(13, 'Xét nghiệm Nhóm máu (ABO, Rh)', 60000.00, 'CLS_XET_NGHIEM', 4, 7),
(14, 'Xét nghiệm Viêm gan B (HBsAg test nhanh)', 100000.00, 'CLS_XET_NGHIEM', 4, 7),
(15, 'Siêu âm ổ bụng tổng quát', 150000.00, 'CLS_CHAN_DOAN_HINH_ANH', 3, 12),
(16, 'Siêu âm Tuyến giáp', 150000.00, 'CLS_CHAN_DOAN_HINH_ANH', 3, 12),
(17, 'Siêu âm Tim', 300000.00, 'CLS_CHAN_DOAN_HINH_ANH', 3, 12),
(18, 'Chụp X-Quang Phổi thẳng', 120000.00, 'CLS_CHAN_DOAN_HINH_ANH', 3, 12),
(19, 'Chụp X-Quang Xương khớp (1 vị trí)', 120000.00, 'CLS_CHAN_DOAN_HINH_ANH', 3, 12),
(20, 'Đo Điện tim (ECG)', 100000.00, 'CLS_CHAN_DOAN_HINH_ANH', 3, 12),
(31, 'Chụp X-quang răng (tại ghế)', 50000.00, 'CLS_CHAN_DOAN_HINH_ANH', 7, 12);

-- --------------------------------------------------------

--
-- Table structure for table `hoa_don`
--

DROP TABLE IF EXISTS `hoa_don`;
CREATE TABLE IF NOT EXISTS `hoa_don` (
  `ma_hoa_don` int NOT NULL AUTO_INCREMENT,
  `ma_phieu_kham` int NOT NULL,
  `ma_nhan_vien` int NOT NULL,
  `tong_tien` decimal(15,2) DEFAULT NULL,
  `ngay_thanh_toan` datetime DEFAULT CURRENT_TIMESTAMP,
  `ghi_chu` text,
  `trang_thai` varchar(20) NOT NULL DEFAULT '"chua thanh toan"',
  PRIMARY KEY (`ma_hoa_don`),
  KEY `ma_phieu_kham` (`ma_phieu_kham`),
  KEY `ma_thu_ngan` (`ma_nhan_vien`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ket_qua_cdha`
--

DROP TABLE IF EXISTS `ket_qua_cdha`;
CREATE TABLE IF NOT EXISTS `ket_qua_cdha` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_chi_tiet_chi_dinh` int NOT NULL,
  `mo_ta_hinh_anh` text,
  `ket_luan` text,
  `de_nghi` text,
  `duong_dan_anh_1` text,
  `duong_dan_anh_2` varchar(255) DEFAULT NULL,
  `ngay_thuc_hien` datetime DEFAULT CURRENT_TIMESTAMP,
  `ma_bac_si_thuc_hien` int DEFAULT NULL,
  `ma_nhan_vien_thuc_hien` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_MACT` (`id_chi_tiet_chi_dinh`),
  KEY `fk_mabs` (`ma_bac_si_thuc_hien`),
  KEY `FK_MANVTHUCHIEN` (`ma_nhan_vien_thuc_hien`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ket_qua_xet_nghiem`
--

DROP TABLE IF EXISTS `ket_qua_xet_nghiem`;
CREATE TABLE IF NOT EXISTS `ket_qua_xet_nghiem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_chi_tiet_chi_dinh` int NOT NULL,
  `ngay_thuc_hien` datetime DEFAULT CURRENT_TIMESTAMP,
  `nguoi_thuc_hien` int DEFAULT NULL,
  `ma_bs_ket_luan` int DEFAULT NULL,
  `ket_luan` text,
  `hong_cau` float DEFAULT NULL,
  `bach_cau` float DEFAULT NULL,
  `tieu_cau` float DEFAULT NULL,
  `huyet_sac_to` float DEFAULT NULL,
  `hematocrit` float DEFAULT NULL,
  `nhom_mau` varchar(10) DEFAULT NULL,
  `toc_do_mau_lang` int DEFAULT NULL,
  `dong_mau_co_ban` text,
  `glucose` float DEFAULT NULL,
  `hbA1c` float DEFAULT NULL,
  `ure` float DEFAULT NULL,
  `creatinine` float DEFAULT NULL,
  `ast_got` float DEFAULT NULL,
  `alt_gpt` float DEFAULT NULL,
  `ggt` float DEFAULT NULL,
  `cholesterol_tp` float DEFAULT NULL,
  `triglyceride` float DEFAULT NULL,
  `hdl_c` float DEFAULT NULL,
  `ldl_c` float DEFAULT NULL,
  `acid_uric` float DEFAULT NULL,
  `bilirubin_tp` float DEFAULT NULL,
  `ty_trong` float DEFAULT NULL,
  `ph` float DEFAULT NULL,
  `bach_cau_nuoc_tieu` varchar(20) DEFAULT NULL,
  `hong_cau_nuoc_tieu` varchar(20) DEFAULT NULL,
  `protein_nuoc_tieu` varchar(20) DEFAULT NULL,
  `duong_nuoc_tieu` varchar(20) DEFAULT NULL,
  `nitrit` varchar(20) DEFAULT NULL,
  `ketone` varchar(20) DEFAULT NULL,
  `hbsag` varchar(50) DEFAULT NULL,
  `hcv_ab` varchar(50) DEFAULT NULL,
  `hiv_ab` varchar(50) DEFAULT NULL,
  `tpha_syphilis` varchar(50) DEFAULT NULL,
  `crp_dinh_luong` float DEFAULT NULL,
  `rf_dinh_luong` float DEFAULT NULL,
  `ghi_chu_them` text,
  PRIMARY KEY (`id`),
  KEY `FK_TK_NV12` (`nguoi_thuc_hien`),
  KEY `Fk_mactchidinh` (`ma_chi_tiet_chi_dinh`),
  KEY `FK_TK_NV13` (`ma_bs_ket_luan`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `kham_lam_sang`
--

DROP TABLE IF EXISTS `kham_lam_sang`;
CREATE TABLE IF NOT EXISTS `kham_lam_sang` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_phieu_kham` int NOT NULL,
  `ly_do_kham` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `tien_su_ban_than` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `benh_su` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `chan_doan_so_bo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `loi_dan_bac_si` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `ket_qua_kham_can_lam_sang` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `kham_lam_sang` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ma_phieu_kham` (`ma_phieu_kham`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `kho_thuoc`
--

DROP TABLE IF EXISTS `kho_thuoc`;
CREATE TABLE IF NOT EXISTS `kho_thuoc` (
  `id_kho` int NOT NULL AUTO_INCREMENT,
  `ma_thuoc` int NOT NULL,
  `so_luong_ton` int NOT NULL DEFAULT '0',
  `ngay_cap_nhat_cuoi` datetime NOT NULL,
  PRIMARY KEY (`id_kho`),
  UNIQUE KEY `ma_thuoc` (`ma_thuoc`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `kho_thuoc`
--

INSERT INTO `kho_thuoc` (`id_kho`, `ma_thuoc`, `so_luong_ton`, `ngay_cap_nhat_cuoi`) VALUES
(1, 1, 5495, '2025-11-24 22:13:27'),
(2, 3, 1110, '2025-11-24 23:17:55'),
(3, 2, 8, '2025-12-25 22:04:25'),
(5, 5, 292, '2025-12-19 17:04:29'),
(6, 6, 40, '2025-12-19 17:04:29'),
(7, 7, 380, '2025-12-19 17:04:29'),
(8, 8, 16, '2025-12-19 17:04:29'),
(9, 9, 4340, '2025-12-19 17:04:29'),
(10, 10, 200, '2025-12-19 17:04:29'),
(11, 11, 6, '2025-12-19 17:04:29'),
(12, 12, 120, '2025-12-19 17:04:29'),
(13, 13, 20, '2025-12-19 17:04:29');

-- --------------------------------------------------------

--
-- Table structure for table `lich_tai_kham`
--

DROP TABLE IF EXISTS `lich_tai_kham`;
CREATE TABLE IF NOT EXISTS `lich_tai_kham` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_benh_nhan` int NOT NULL,
  `ma_chuyen_khoa` int NOT NULL,
  `ma_phieu_kham` int DEFAULT NULL,
  `ma_nhan_vien` int NOT NULL,
  `ngay_tai_kham` date NOT NULL,
  `ghi_chu` text,
  `trang_thai` enum('CHUA_DEN','DA_DEN','HOAN') DEFAULT 'CHUA_DEN',
  `da_gui_thong_bao` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `ma_benh_nhan` (`ma_benh_nhan`),
  KEY `ma_phieu_kham` (`ma_phieu_kham`),
  KEY `fk_lichtaikhamnhanvien` (`ma_nhan_vien`),
  KEY `fk_idck` (`ma_chuyen_khoa`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `nhan_vien`
--

DROP TABLE IF EXISTS `nhan_vien`;
CREATE TABLE IF NOT EXISTS `nhan_vien` (
  `ma_nhan_vien` int NOT NULL AUTO_INCREMENT,
  `ho_ten` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gioi_tinh` bit(2) DEFAULT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `so_dien_thoai` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `chuyen_khoa` varchar(30) DEFAULT NULL,
  `bang_cap` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `chuc_vu` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `ngay_vao_lam` date DEFAULT NULL,
  `cccd` varchar(20) NOT NULL,
  PRIMARY KEY (`ma_nhan_vien`),
  UNIQUE KEY `unique_cccd` (`cccd`),
  UNIQUE KEY `unique_email` (`email`),
  UNIQUE KEY `unique_sdt` (`so_dien_thoai`),
  KEY `FK_CV_NV` (`chuc_vu`)
) ENGINE=InnoDB AUTO_INCREMENT=261 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `nhan_vien`
--

INSERT INTO `nhan_vien` (`ma_nhan_vien`, `ho_ten`, `gioi_tinh`, `ngay_sinh`, `dia_chi`, `so_dien_thoai`, `email`, `chuyen_khoa`, `bang_cap`, `chuc_vu`, `ngay_vao_lam`, `cccd`) VALUES
(214, 'thanh tháo', b'01', '2000-11-28', 'HCM', '0123456782', 'bstq@gmail.com', 'Nội tổng quát', 'đại học bách khoa', 'Bác sĩ', '2025-11-29', '123456789124'),
(215, 'Lê Văn Lộc', b'01', '1990-05-12', 'HCM', '0901000101', 'bstq2@gmail.com', 'Nội tổng quát', 'Thạc sĩ Y', 'Bác sĩ', '2026-01-14', '12345678912401'),
(216, 'trúc minh', b'01', '2000-11-28', 'HCM', '0123456783', 'nhikhoa@gmail.com', 'Nhi khoa', 'đại học bách khoa', 'Bác sĩ', '2025-11-29', '123456789125'),
(217, 'Phạm Minh Anh', b'00', '1993-08-20', 'HCM', '0901000102', 'nhikhoa2@gmail.com', 'Nhi khoa', 'Đại học Y Dược', 'Bác sĩ', '2026-01-14', '12345678912501'),
(218, 'minh tâm', b'01', '2000-11-28', 'HCM', '0123456784', 'taimuihong@gmail.com', 'Tai - Mũi - Họng', 'đại học bách khoa', 'Bác sĩ', '2025-11-29', '123456789126'),
(219, 'Ngô Quốc Huy', b'01', '1989-12-01', 'HCM', '0901000103', 'tmh2@gmail.com', 'Tai - Mũi - Họng', 'Bác sĩ CKI', 'Bác sĩ', '2026-01-14', '12345678912601'),
(220, 'thành phát', b'01', '2000-11-28', 'HCM', '0123456785', 'ranghammat@gmail.com', 'Răng - Hàm - Mặt', 'đại học bách khoa', 'Bác sĩ', '2025-11-29', '123456789111'),
(221, 'Trần Mỹ Dung', b'00', '1994-06-15', 'HCM', '0901000104', 'rhm2@gmail.com', 'Răng - Hàm - Mặt', 'Đại học Y', 'Bác sĩ', '2026-01-14', '12345678911101'),
(222, 'trọng phúc', b'01', '2000-11-28', 'HCM', '0123456786', 'xetnghiem@gmail.com', 'Xét nghiệm', 'đại học bách khoa', 'Bác sĩ', '2025-11-29', '123456789321'),
(223, 'Hoàng Thu Trang', b'00', '1995-11-11', 'HCM', '0901000105', 'bsxn2@gmail.com', 'Xét nghiệm', 'Thạc sĩ Y', 'Bác sĩ', '2026-01-14', '12345678932101'),
(224, 'hồng hà', b'01', '2000-11-28', 'HCM', '0223456789', 'timmach@gmail.com', 'Tim mạch', 'đại học bách khoa', 'Bác sĩ', '2025-11-29', '123456789129'),
(225, 'Đặng Tiến Dũng', b'01', '1987-03-30', 'HCM', '0901000106', 'timmach2@gmail.com', 'Tim mạch', 'Bác sĩ CKII', 'Bác sĩ', '2026-01-14', '12345678912901'),
(226, 'thái hồ', b'01', '2000-12-14', 'HCM', '0123451234', 'cdha@gmail.com', 'Chẩn đoán hình ảnh', 'ĐẠI HỌC SÀI GÒN', 'Bác sĩ', '2025-12-03', '123456781234'),
(227, 'Bùi Thanh Sơn', b'01', '1991-09-09', 'HCM', '0901000107', 'cdha2@gmail.com', 'Chẩn đoán hình ảnh', 'Đại học Y', 'Bác sĩ', '2026-01-14', '12345678123401'),
(228, 'thanh minh', b'01', '2000-11-28', 'HCM', '0123456789', 'trolynhikhoa@gmail.com', 'Nhi khoa', 'đại học bách khoa', 'Trợ lý bác sĩ chuyên khoa', '2025-11-29', '523456789123'),
(229, 'Nguyễn Phương Thảo', b'00', '2002-05-05', 'HCM', '0902000101', 'tl_nhi2@gmail.com', 'Nhi khoa', 'Cao đẳng Y', 'Trợ lý bác sĩ chuyên khoa', '2026-01-14', '52345678912301'),
(230, 'thịnh phát', b'01', '2000-11-28', 'HCM', '0987654309', 'trolytaimuihong@gmail.com', 'Tai - Mũi - Họng', 'đại học bách khoa', 'Trợ lý bác sĩ chuyên khoa', '2025-11-29', '113456789123'),
(231, 'Lý Hải Đăng', b'01', '2001-02-12', 'HCM', '0902000102', 'tl_tmh2@gmail.com', 'Tai - Mũi - Họng', 'Trung cấp Y', 'Trợ lý bác sĩ chuyên khoa', '2026-01-14', '11345678912301'),
(232, 'kiều duyên', b'01', '2000-11-28', 'HCM', '0989898998', 'trolyranghammat@gmail.com', 'Răng - Hàm - Mặt', 'đại học bách khoa', 'Trợ lý bác sĩ chuyên khoa', '2025-11-29', '123456789345'),
(233, 'Vũ Minh Nguyệt', b'00', '2001-10-20', 'HCM', '0902000103', 'tl_rhm2@gmail.com', 'Răng - Hàm - Mặt', 'Cao đẳng Y', 'Trợ lý bác sĩ chuyên khoa', '2026-01-14', '12345678934501'),
(234, 'thảo nguyên', b'01', '2000-11-28', 'HCM', '0939768936', 'trolytimmach@gmail.com', 'Tim mạch', 'đại học bách khoa', 'Trợ lý bác sĩ chuyên khoa', '2025-11-29', '123456789000'),
(235, 'Trần Văn Tám', b'01', '1998-03-03', 'HCM', '0902000104', 'tl_tmach2@gmail.com', 'Tim mạch', 'Trung cấp Y', 'Trợ lý bác sĩ chuyên khoa', '2026-01-14', '12345678900001'),
(236, 'minh thúy', b'00', '2000-11-28', 'HCM', '0123456733', 'trolytq@gmail.com', 'Nội tổng quát', 'đại học bách khoa', 'Trợ lý bác sĩ tổng quát', '2025-11-29', '123456789888'),
(237, 'Lê Kiều Oanh', b'00', '1999-12-12', 'HCM', '0902000105', 'tl_tq2@gmail.com', 'Nội tổng quát', 'Cao đẳng Điều dưỡng', 'Trợ lý bác sĩ tổng quát', '2026-01-14', '12345678988801'),
(238, 'thanh hà', b'01', '2000-11-28', 'HCM', '0233456789', 'letan@gmail.com', '', 'đại học bách khoa', 'Lễ tân', '2025-11-29', '1763456789123'),
(239, 'manh vũ', b'01', '2000-12-14', 'HCM', '0123451298', 'letan1@gmail.com', '', 'ĐẠI HỌC SÀI GÒN', 'Lễ tân', '2025-12-03', '123456787689'),
(240, 'thành minh', b'01', '2000-11-28', 'HCM', '0876456789', 'thungan@gmail.com', '', 'đại học bách khoa', 'Thu ngân', '2025-11-29', '123456789112'),
(241, 'Bùi Bích Phương', b'00', '1997-04-14', 'HCM', '0903000101', 'thungan2@gmail.com', '', 'Đại học Tài chính', 'Thu ngân', '2026-01-14', '12345678911201'),
(242, 'khánh minh', b'01', '2000-11-28', 'HCM', '0113456789', 'duocsi@gmail.com', '', 'đại học bách khoa', 'Dược sĩ', '2025-11-29', '223456789123'),
(243, 'Trần Anh Tuấn', b'01', '1995-01-01', 'HCM', '0903000102', 'duocsi2@gmail.com', '', 'Đại học Y Dược', 'Dược sĩ', '2026-01-14', '22345678912301'),
(244, 'minh tân', b'01', '2000-11-28', 'HCM', '0123312321', 'kho@gmail.com', '', 'đại học bách khoa', 'Nhân viên kho', '2025-11-29', '987654321123'),
(245, 'Lý Gia Thành', b'01', '1992-06-06', 'HCM', '0903000103', 'kho2@gmail.com', '', 'Đại học GTVT', 'Nhân viên kho', '2026-01-14', '98765432112301'),
(247, 'Nguyễn Văn Nam', b'01', '1998-07-07', 'HCM', '0903000104', 'ktv_xn2@gmail.com', 'Xét nghiệm', 'Đại học Y', 'Kỹ thuật viên Xét nghiệm', '2026-01-14', '12345678876501'),
(248, 'Phúc lộc thọ', b'01', '2000-12-14', 'HCM', '00123451212', 'ktvcdha@gmail.com', 'Chẩn đoán hình ảnh', 'ĐẠI HỌC SÀI GÒN', 'Kỹ thuật viên Chẩn đoán hình ảnh', '2025-12-03', '123456781212'),
(249, 'Hà Quang Hải', b'01', '1996-04-04', 'HCM', '0903000105', 'ktv_ha2@gmail.com', 'Chẩn đoán hình ảnh', 'Đại học Bách Khoa', 'Kỹ thuật viên Chẩn đoán hình ảnh', '2026-01-14', '12345678121201'),
(250, 'admin', b'01', '2000-12-14', 'HCM', '0123456943', 'tamduonge7@gmail.com', '', 'ĐẠI HỌC SÀI GÒN', 'Quản trị viên', '2025-12-03', '123456789011'),
(251, 'Thu Hà', b'01', '1998-07-07', 'HCM', '00903000123', 'ktv_xn1@gmail.com', 'Xét nghiệm', 'Đại học Y', 'Kỹ thuật viên Xét nghiệm', '2026-01-14', '12345678876561');

-- --------------------------------------------------------

--
-- Table structure for table `nha_cung_cap`
--

DROP TABLE IF EXISTS `nha_cung_cap`;
CREATE TABLE IF NOT EXISTS `nha_cung_cap` (
  `ma_nha_cung_cap` int NOT NULL AUTO_INCREMENT,
  `ten_nha_cung_cap` varchar(100) NOT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `email` varchar(20) NOT NULL,
  `ghi_chu` text,
  `so_dien_thoai` varchar(20) NOT NULL,
  PRIMARY KEY (`ma_nha_cung_cap`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `so_dien_thoai` (`so_dien_thoai`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `nha_cung_cap`
--

INSERT INTO `nha_cung_cap` (`ma_nha_cung_cap`, `ten_nha_cung_cap`, `dia_chi`, `email`, `ghi_chu`, `so_dien_thoai`) VALUES
(1, 'Công ty Dược phẩm Thành Công1', '200 Lạc Long Quân, Hà Nội', 'thanhcong@gmail.com', '', '2438889999'),
(3, 'cc', '', 'cc_supplier@gmail.co', '', '01123213211'),
(4, 'Công ty CP Dược Hậu Giang', '288 Bis Nguyễn Văn Cừ, Cần Thơ', 'dhg@dhgpharma.com.vn', NULL, '02923891433'),
(5, 'Công ty CP Traphaco', '75 Yên Ninh, Ba Đình, Hà Nội', 'info@traphaco.com.vn', NULL, '02437666612'),
(6, 'Công ty TNHH MTV Dược Sài Gòn', '18-20 Nguyễn Trường Tộ, Q.4, TP.HCM', 'contact@sapharco.com', NULL, '02839400388'),
(7, 'Công ty CP Dược phẩm Imexpharm', 'Số 4, đường 30/4, Cao Lãnh, Đồng Tháp', 'imexpharm@imexpharm.', NULL, '02773851941'),
(8, 'Công ty CP Dược phẩm Trung ương CPC1', '87 Nguyễn Huy Tưởng, Thanh Xuân, Hà Nội', 'cpc1@cpc1.com.vn', NULL, '02438583543'),
(20, 'Công ty Dược phẩm Thành Công', '200 Lạc Long Quân, Hà Nội', 'thanhcong1@gmail.com', '', '02438889998');

-- --------------------------------------------------------

--
-- Table structure for table `phieu_chi_dinh`
--

DROP TABLE IF EXISTS `phieu_chi_dinh`;
CREATE TABLE IF NOT EXISTS `phieu_chi_dinh` (
  `ma_phieu_chi_dinh` int NOT NULL AUTO_INCREMENT,
  `ma_phieu_kham` int NOT NULL,
  `ma_nhan_vien_chi_dinh` int NOT NULL,
  `ngay_chi_dinh` datetime DEFAULT CURRENT_TIMESTAMP,
  `tong_tien` decimal(15,2) DEFAULT '0.00',
  PRIMARY KEY (`ma_phieu_chi_dinh`),
  KEY `fk-manvv` (`ma_nhan_vien_chi_dinh`),
  KEY `fk-maphieukham` (`ma_phieu_kham`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieu_chuyen_khoa`
--

DROP TABLE IF EXISTS `phieu_chuyen_khoa`;
CREATE TABLE IF NOT EXISTS `phieu_chuyen_khoa` (
  `ma_phieu_chuyen` int NOT NULL AUTO_INCREMENT,
  `ma_phieu_kham` int NOT NULL,
  `ma_benh_nhan` int NOT NULL,
  `ma_nhan_vien` int NOT NULL COMMENT 'Bác sĩ chỉ định chuyển',
  `ma_ck_tu` int NOT NULL,
  `ma_ck_den` int NOT NULL,
  `tom_tat_lam_sang` text,
  `ly_do_chuyen` text,
  `ngay_chuyen` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_dang_ky_moi` int DEFAULT NULL,
  PRIMARY KEY (`ma_phieu_chuyen`),
  KEY `fk_phieuchuyenkhoa_maphieukham` (`ma_phieu_kham`),
  KEY `fk_phieuchuyenkhoa_manv` (`ma_nhan_vien`),
  KEY `fk_phieuchuyenkhoa_mackchuyentu` (`ma_ck_tu`),
  KEY `fk_phieuchuyenkhoa_machuyenkhoaden` (`ma_ck_den`),
  KEY `fk_phieuchuyenkhoa_iddangkymoi` (`id_dang_ky_moi`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieu_kham`
--

DROP TABLE IF EXISTS `phieu_kham`;
CREATE TABLE IF NOT EXISTS `phieu_kham` (
  `ma_phieu_kham` int NOT NULL AUTO_INCREMENT,
  `ma_benh_nhan` int NOT NULL,
  `ma_nhan_vien` int NOT NULL,
  `ma_chuyen_khoa` int DEFAULT NULL,
  `ngay_kham` datetime DEFAULT CURRENT_TIMESTAMP,
  `trang_thai` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'Đang khám',
  `ghi_chu` text,
  `ngay_tao` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ma_phieu_kham`),
  KEY `ma_benh_nhan` (`ma_benh_nhan`),
  KEY `ma_nhan_vien` (`ma_nhan_vien`),
  KEY `ma_chuyen_khoa` (`ma_chuyen_khoa`)
) ENGINE=InnoDB AUTO_INCREMENT=587 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieu_nhap_thuoc`
--

DROP TABLE IF EXISTS `phieu_nhap_thuoc`;
CREATE TABLE IF NOT EXISTS `phieu_nhap_thuoc` (
  `ma_phieu_nhap_thuoc` int NOT NULL AUTO_INCREMENT,
  `ngay_nhap` datetime NOT NULL,
  `ma_nhan_vien_nhap` int NOT NULL,
  `ma_nha_cung_cap` int DEFAULT NULL,
  `tong_tien_nhap` decimal(18,2) DEFAULT '0.00',
  `trang_thai` varchar(50) NOT NULL DEFAULT 'Hoàn thành',
  `ghi_chu` text,
  PRIMARY KEY (`ma_phieu_nhap_thuoc`),
  KEY `ma_nhan_vien_nhap` (`ma_nhan_vien_nhap`),
  KEY `ma_nha_cung_cap` (`ma_nha_cung_cap`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `phieu_nhap_thuoc`
--

INSERT INTO `phieu_nhap_thuoc` (`ma_phieu_nhap_thuoc`, `ngay_nhap`, `ma_nhan_vien_nhap`, `ma_nha_cung_cap`, `tong_tien_nhap`, `trang_thai`, `ghi_chu`) VALUES
(6, '2025-11-20 20:13:51', 245, 1, 50000.00, 'Hoàn thành', NULL),
(7, '2025-11-20 20:21:38', 245, 1, 0.00, 'Đang tạo', NULL),
(8, '2025-11-20 20:22:28', 245, 1, 25000.00, 'Hoàn thành', NULL),
(9, '2025-11-20 20:39:30', 245, 1, 5000.00, 'Hoàn thành', NULL),
(10, '2025-11-20 21:01:02', 245, 1, 0.00, 'Đang tạo', NULL),
(11, '2025-11-20 21:08:01', 245, 1, 5000.00, 'Hoàn thành', NULL),
(12, '2025-11-20 23:36:44', 245, 1, 500000.00, 'Hoàn thành', NULL),
(13, '2025-11-24 22:12:10', 245, 3, 0.00, 'Đang tạo', NULL),
(14, '2025-11-24 22:13:23', 245, 1, 50000.00, 'Hoàn thành', NULL),
(15, '2025-11-24 22:17:27', 245, 3, 2000000.00, 'Hoàn thành', NULL),
(16, '2025-11-24 22:19:23', 245, 3, 0.00, 'Đang tạo', NULL),
(17, '2025-11-24 22:44:04', 245, 3, 0.00, 'Đang tạo', NULL),
(18, '2025-11-24 22:45:23', 245, 1, 0.00, 'Đang tạo', NULL),
(19, '2025-11-24 22:50:52', 245, 1, 0.00, 'Đang tạo', NULL),
(20, '2025-11-24 23:07:17', 245, 1, 8700000.00, 'Hoàn thành', NULL),
(22, '2025-12-25 22:03:49', 245, 1, 121000.00, 'Hoàn thành', NULL),
(23, '2026-01-14 15:54:06', 244, 1, 0.00, 'Đang tạo', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `phong_chuc_nang`
--

DROP TABLE IF EXISTS `phong_chuc_nang`;
CREATE TABLE IF NOT EXISTS `phong_chuc_nang` (
  `ma_phong` int NOT NULL AUTO_INCREMENT,
  `ten_phong` varchar(50) NOT NULL,
  `loai_phong` varchar(50) NOT NULL,
  `ma_chuyen_khoa` int DEFAULT NULL,
  `ma_chuc_vu` int DEFAULT NULL,
  PRIMARY KEY (`ma_phong`),
  UNIQUE KEY `ten_phong` (`ten_phong`),
  KEY `fk_phong_chuyenkhoa` (`ma_chuyen_khoa`),
  KEY `fk_phong_chucvu` (`ma_chuc_vu`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `phong_chuc_nang`
--

INSERT INTO `phong_chuc_nang` (`ma_phong`, `ten_phong`, `loai_phong`, `ma_chuyen_khoa`, `ma_chuc_vu`) VALUES
(1, 'P. Khám Nội Tổng Quát', 'KHOA_NOI', 1, 16),
(3, 'P. Chẩn Đoán Hình ảnh', 'CLS_CHAN_DOAN_HINH_ANH', 12, 16),
(4, 'P. Xét nghiệm', 'CLS_XET_NGHIEM', 7, 16),
(5, 'P. Khám Nhi Khoa', 'KHAM_CHUYEN_KHOA', 3, 16),
(6, 'P.Tai - Mũi - Họng', 'KHAM_CHUYEN_KHOA', 4, 16),
(7, 'P. Răng - Hàm - Mặt', 'KHAM_CHUYEN_KHOA', 5, 16),
(8, 'P. Tim mạch', 'KHAM_CHUYEN_KHOA', 11, 16),
(13, 'Quầy Tiếp Đón & Lễ Tân', 'LE_TAN', NULL, 3),
(14, 'Quầy Thuốc (Cấp phát)', 'NHA_THUOC', NULL, 5),
(15, 'Kho Dược Chính', 'KHO_DUOC', NULL, 6),
(16, 'Quầy thu ngân', 'thu tiền', NULL, 4);

-- --------------------------------------------------------

--
-- Table structure for table `tai_khoan`
--

DROP TABLE IF EXISTS `tai_khoan`;
CREATE TABLE IF NOT EXISTS `tai_khoan` (
  `ma_tai_khoan` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(100) NOT NULL,
  `mat_khau` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ma_nhan_vien` int NOT NULL,
  `vai_tro` varchar(50) DEFAULT NULL,
  `lan_dau_dang_nhap` bit(1) DEFAULT b'1',
  PRIMARY KEY (`ma_tai_khoan`),
  KEY `FK_TK_NV` (`ma_nhan_vien`),
  KEY `FK_TaiKhoan_VaiTro` (`vai_tro`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tai_khoan`
--

INSERT INTO `tai_khoan` (`ma_tai_khoan`, `username`, `email`, `mat_khau`, `ma_nhan_vien`, `vai_tro`, `lan_dau_dang_nhap`) VALUES
(30, 'thanhtrúc', 'bstq@gmail.com', '$2a$05$Ypby2NcBJAm/AnYSr/zFeujA58hir4IVF4QQ42AMx94XmTgy3GHd2', 214, 'BAC_SI', b'0'),
(31, 'vanloc', 'bstq2@gmail.com', '$2a$05$3Fa/n1bXynIKTbogPjLl8uD2MwsT9RQKaqEvJnUWfvkTLu32u6aoG', 215, 'BAC_SI', b'1'),
(32, 'thanhtrúc', 'nhikhoa@gmail.com', '$2a$05$eiNW8wSnzM4uV6A4bIVmGeALGD5fVNo2iH1G3LkkLV2lwbX6Qb0Le', 216, 'BAC_SI', b'1'),
(33, 'minhanh', 'nhikhoa2@gmail.com', '$2a$05$eiNW8wSnzM4uV6A4bIVmGeALGD5fVNo2iH1G3LkkLV2lwbX6Qb0Le', 217, 'BAC_SI', b'1'),
(34, 'thanh trúc', 'taimuihong@gmail.com', '$2a$05$xDtjmOcE8fMWpjVuukM6numPqGEheY.6aEX8PpcxoDezv3BqxaHqy', 218, 'BAC_SI', b'1'),
(35, 'quochuy', 'tmh2@gmail.com', '$2a$05$xDtjmOcE8fMWpjVuukM6numPqGEheY.6aEX8PpcxoDezv3BqxaHqy', 219, 'BAC_SI', b'1'),
(36, 'thanh trúc', 'ranghammat@gmail.com', '$2a$05$H//MuIK3rGDbkhZQGhVy.OWHdKeWhKOO7BbOCMna6Tu1NuQ9qbS16', 220, 'BAC_SI', b'0'),
(37, 'mydung', 'rhm2@gmail.com', '$2a$05$WhQTEZKeq7NdK2nS4XN7V.XeWoI7YdMm1sRtETSsj/RSYBIKyE3oK', 221, 'BAC_SI', b'1'),
(38, 'thanh trúc', 'xetnghiem@gmail.com', '$2a$05$IyQ3P5IA7ut5p1zfGU3c3eVQrrgKiQ/NSOaTFYwG1VRfYQ9livheS', 222, 'BAC_SI', b'0'),
(39, 'thutrang', 'bsxn2@gmail.com', '$2a$05$5N1Br43TJNBWGXzp.4FqcO2xAW1f47gMGykE8fnMVB5jtKREXuhgW', 223, 'BAC_SI', b'1'),
(40, 'thanh trúc', 'timmach@gmail.com', '$2a$05$50t6x1cFnZApuiTSlc7ieeZ.gLCVD5h1dCCTIYhIaMAkS8HIyFewW', 224, 'BAC_SI', b'1'),
(41, 'tiendung', 'timmach2@gmail.com', '$2a$05$50t6x1cFnZApuiTSlc7ieeZ.gLCVD5h1dCCTIYhIaMAkS8HIyFewW', 225, 'BAC_SI', b'1'),
(42, 'thu hà', 'cdha@gmail.com', '$2a$05$i3ddtme1FcUd48wkg0WriO/GO5inRyDwR7r8druSzYuSj2awT.gb2', 226, 'BAC_SI', b'0'),
(43, 'thanhson', 'cdha2@gmail.com', '$2a$05$oko8F6eIvjgEymkcyx.gL.FatsG0EzeqM5D0zHFkP5dUs6IV2F/mq', 227, 'BAC_SI', b'1'),
(44, 'hồng hà', 'trolynhikhoa@gmail.com', '$2a$05$ZRIHA.NujJVmPxs/ojNu9uxD2JMMUkppA1jJyO5jlVXzIYv6nR69u', 228, 'TRO_LY_BAC_SI_CHUYEN_KHOA', b'1'),
(45, 'phuongthao', 'tl_nhi2@gmail.com', '$2a$05$ZRIHA.NujJVmPxs/ojNu9uxD2JMMUkppA1jJyO5jlVXzIYv6nR69u', 229, 'TRO_LY_BAC_SI_CHUYEN_KHOA', b'1'),
(46, 'hồng hà', 'trolytaimuihong@gmail.com', '$2a$05$mogv2xXKtGELKAPVRQ37Veje6lzBbi4owDvOXoGITnvZ79pVZ4Qny', 230, 'TRO_LY_BAC_SI_CHUYEN_KHOA', b'1'),
(47, 'haidang', 'tl_tmh2@gmail.com', '$2a$05$mogv2xXKtGELKAPVRQ37Veje6lzBbi4owDvOXoGITnvZ79pVZ4Qny', 231, 'TRO_LY_BAC_SI_CHUYEN_KHOA', b'1'),
(48, 'hồng hà', 'trolyranghammat@gmail.com', '$2a$05$gJjT.GU6AmIvfJkNlsmwtuQZPf8zylSOZUpb9YvNuFAB6d1RAhC4i', 232, 'TRO_LY_BAC_SI_CHUYEN_KHOA', b'0'),
(49, 'minhnguyet', 'tl_rhm2@gmail.com', '$2a$05$26uF6/qKwq6oNvddPY51fOgfSvmc4k586LQbaynG5CYoFv58PLz8K', 233, 'TRO_LY_BAC_SI_CHUYEN_KHOA', b'1'),
(50, 'hồng hà', 'trolytimmach@gmail.com', '$2a$05$skyDWA4XJ/0V/zQHjCIVMuFfQNmMwgno0dxV7BujlNEwLGeJL.lp.', 234, 'TRO_LY_BAC_SI_CHUYEN_KHOA', b'1'),
(51, 'vantam', 'tl_tmach2@gmail.com', '$2a$05$skyDWA4XJ/0V/zQHjCIVMuFfQNmMwgno0dxV7BujlNEwLGeJL.lp.', 235, 'TRO_LY_BAC_SI_CHUYEN_KHOA', b'1'),
(52, 'minh thúy', 'trolytq@gmail.com', '$2a$05$OS5ErI6rrxy5g0HZJgqi5uDmHBcKG5fkz.A0LSbRUJxT6xvw0VUpS', 236, 'TRO_LY_BAC_SI_TONG_QUAT', b'0'),
(53, 'kieuoanh', 'tl_tq2@gmail.com', '$2a$05$iCjwXZ4/fwKYGKWZy3sZ5O.5/OaZbTXbscFbz7PGWoNLDyCMwKbMG', 237, 'TRO_LY_BAC_SI_TONG_QUAT', b'1'),
(54, 'thanh hà', 'letan@gmail.com', '$2a$05$MoA.4BOcFKeLZhDqYjulY.ov6IPGHk5ABt3DQpQRqjC/3IO.HQl06', 238, 'LE_TAN', b'0'),
(55, 'manh vũ', 'letan1@gmail.com', '$2a$05$Pbs4QzvSPhwUL5ITTLcB7exId85Jtrppz3mOduVBcZXVlCVyeC06a', 239, 'LE_TAN', b'1'),
(56, 'thành minh', 'thungan@gmail.com', '$2a$05$38y/vhPWwjw58cqs9cry4uyOXqDEbUvsgMMRhTXSjESi7D3HLExwe', 240, 'THU_NGAN', b'0'),
(57, 'bichphuong', 'thungan2@gmail.com', '$2a$05$2lz/vm0GxbjZFta1vHBaVe/6c3sLHMbQ8BK/bnUdIhmAtLvaUhpbO', 241, 'THU_NGAN', b'1'),
(58, 'khánh minh', 'duocsi@gmail.com', '$2a$05$X3xMW9uYuIzrtjDqTiWIw.FILMo9GJXNd/77Q.MCzdALOLitkFmIu', 242, 'DUOC_SI', b'0'),
(59, 'anhtuan', 'duocsi2@gmail.com', '$2a$05$furhYWKDqJfHyY5ZzxNKEOa7JNXZPTBa2rHsWeuGCKOmzftF6CaqC', 243, 'DUOC_SI', b'1'),
(60, 'minh tân', 'kho@gmail.com', '$2a$05$6tWW3m93x9iLwmLXQLSWKuSwrJiU1F4xjxQPydQBpDYX6l9Ndjmcy', 244, 'NHAN_VIEN_KHO', b'0'),
(61, 'giathanh', 'kho2@gmail.com', '$2a$05$R5E8ns23h.ShpBHKID2dAOLGGSkwqXRKyf6svkwFWQrtSmarmGZgG', 245, 'NHAN_VIEN_KHO', b'1'),
(63, 'vannam', 'ktv_xn2@gmail.com', '$2a$05$BIpRycXXarPbvFntEqHIRuhCKOaEYG4xkWRsDwl/8eCeJ41r.1DJ6', 247, 'KY_THUAT_VIEN_XET_NGHIEM', b'1'),
(64, 'Phúc lộc thọ', 'ktvcdha@gmail.com', '$2a$05$qqUDgf3Xb/PeQek3skHro.onVcYq5a5bSM/Kj.A38RCufo4kRs9Ie', 248, 'KY_THUAT_VIEN_CHAN_DOAN_HINH_ANH', b'0'),
(65, 'quanghai', 'ktv_ha2@gmail.com', '$2a$05$yj6UTywv29mYmnB3jQ33qOESPVTsgS0yUm3wJu8GaH/Tq4sEFiPzC', 249, 'KY_THUAT_VIEN_CHAN_DOAN_HINH_ANH', b'1'),
(66, 'admin', 'tamduonge7@gmail.com', '$2a$05$Lawao30l5uDH3QCKlDmgpOL7YhvEsTkPlXbLSF1egw5Q8fpq.sQ4C', 250, 'QUAN_TRI_VIEN', b'0'),
(67, 'Thu Hà', 'ktv_xn1@gmail.com', '$2a$05$KqWvQOR58nGKMXpc4hcdH.OxPwZyEPWgjCx85aJt7W0R1Wmm5Ac5i', 251, 'KY_THUAT_VIEN_XET_NGHIEM', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `thuoc`
--

DROP TABLE IF EXISTS `thuoc`;
CREATE TABLE IF NOT EXISTS `thuoc` (
  `ma_thuoc` int NOT NULL AUTO_INCREMENT,
  `ten_thuoc` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `hoat_chat` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `ham_luong` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `dang_thuoc` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `loai_thuoc` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `don_vi_tinh` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `don_gia_nhap` decimal(15,2) DEFAULT '0.00',
  `don_gia_ban` decimal(15,2) DEFAULT '0.00',
  `ngay_san_xuat` date DEFAULT NULL,
  `han_su_dung` date DEFAULT NULL,
  `nha_san_xuat` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `nuoc_san_xuat` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `ghi_chu` text,
  PRIMARY KEY (`ma_thuoc`),
  UNIQUE KEY `ten_thuoc` (`ten_thuoc`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `thuoc`
--

INSERT INTO `thuoc` (`ma_thuoc`, `ten_thuoc`, `hoat_chat`, `ham_luong`, `dang_thuoc`, `loai_thuoc`, `don_vi_tinh`, `don_gia_nhap`, `don_gia_ban`, `ngay_san_xuat`, `han_su_dung`, `nha_san_xuat`, `nuoc_san_xuat`, `ghi_chu`) VALUES
(1, 'Paracetamol 500mg', 'Paracetamol', '500mg', 'Viên nén', 'Uống', 'viên', 10000.00, 12000.00, '2025-01-10', '2027-01-10', 'DHG Pharma', 'Việt Nam', ''),
(2, 'Vitamin C 1000mg11', 'Ascorbic Acid22', '1000mg2', 'Viên sủi3', 'Uống3', 'viên3', 20000.00, 24000.00, '2024-12-01', '2026-12-01', 'OPC Pharma1', 'Việt Nam1', ''),
(3, 'Nước muối sinh lý 0.9%', 'NaCl 0.9%', NULL, 'Dung dịch', 'Nhỏ', 'chai', 0.00, 12000.00, '2025-02-15', '2027-02-15', 'Bidiphar', 'Việt Nam', NULL),
(5, 'Panadol Extra', 'Paracetamol, Caffeine', '500mg, 65mg', 'Viên nén', 'Giảm đau', 'Vỉ', 0.00, 15000.00, '2024-01-10', '2027-01-10', 'GSK', 'Việt Nam', 'Hộp đỏ'),
(6, 'Augmentin 1g', 'Amoxicillin, Clavulanic', '1000mg', 'Viên nén', 'Kháng sinh', 'Viên', 0.00, 25000.00, '2023-12-01', '2025-12-01', 'GSK', 'Anh', 'Kê đơn'),
(7, 'Berberin Mộc Hương', 'Berberin', '100mg', 'Viên nén', 'Tiêu hóa', 'Lọ', 0.00, 50000.00, '2024-05-05', '2027-05-05', 'Dược TW3', 'Việt Nam', 'Đau bụng'),
(8, 'Smecta', 'Diosmectite', '3g', 'Bột pha', 'Tiêu hóa', 'Gói', 0.00, 4000.00, '2024-02-20', '2026-02-20', 'Ipsen', 'Pháp', 'Tiêu chảy'),
(9, 'Eugica Fort', 'Eucalyptol, Ginger, Menthol', 'N/A', 'Viên nang', 'Hô hấp', 'Hộp', 0.00, 80000.00, '2024-03-15', '2026-03-15', 'Mega We Care', 'Thái Lan', 'Trị ho'),
(10, 'Efferalgan 500mg', 'Paracetamol', '500mg', 'Viên sủi', 'Hạ sốt', 'Viên', 0.00, 6000.00, '2024-06-01', '2026-06-01', 'UPSA', 'Pháp', 'Hòa tan'),
(11, 'Gaviscon Dual Action', 'Sodium alginate', '10ml', 'Hỗn dịch', 'Dạ dày', 'Gói', 0.00, 7500.00, '2023-11-11', '2025-11-11', 'Reckitt Benckiser', 'Anh', 'Trào ngược'),
(12, 'Insulin Lantus', 'Insulin glargine', '100 U/ml', 'Dung dịch', 'Tiểu đường', 'Bút tiêm', 0.00, 350000.00, '2024-01-01', '2025-06-01', 'Sanofi', 'Đức', 'Bảo quản lạnh'),
(13, 'Neurontin', 'Gabapentin', '300mg', 'Viên nang', 'Thần kinh', 'Viên', 0.00, 12000.00, '2023-10-20', '2026-10-20', 'Pfizer', 'Mỹ', 'Kê đơn'),
(14, 'Zyrtec', 'Cetirizine', '10mg', 'Viên nén', 'Dị ứng', 'Vỉ', 0.00, 85000.00, '2024-04-01', '2027-04-01', 'GSK', 'Thụy Sĩ', 'Viêm mũi'),
(16, 'Zyrtec123', 'Cetirizine', '10mg', 'Viên nén', 'Dị ứng', 'Vỉ', 70000.00, 84000.00, '2024-04-01', '2027-04-01', 'GSK', 'Thụy Sĩ', 'Viêm mũi');

-- --------------------------------------------------------

--
-- Table structure for table `toa_thuoc`
--

DROP TABLE IF EXISTS `toa_thuoc`;
CREATE TABLE IF NOT EXISTS `toa_thuoc` (
  `ma_toa_thuoc` int NOT NULL AUTO_INCREMENT,
  `ma_phieu_kham` int NOT NULL,
  `ghi_chu` text,
  `ngay_tao` datetime DEFAULT CURRENT_TIMESTAMP,
  `trang_thai` varchar(30) DEFAULT 'CHO_THANH_TOAN',
  PRIMARY KEY (`ma_toa_thuoc`),
  KEY `ma_phieu_kham` (`ma_phieu_kham`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `vai_tro`
--

DROP TABLE IF EXISTS `vai_tro`;
CREATE TABLE IF NOT EXISTS `vai_tro` (
  `ma_vai_tro` varchar(50) NOT NULL,
  `ten_hien_thi` varchar(50) NOT NULL,
  PRIMARY KEY (`ma_vai_tro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `vai_tro`
--

INSERT INTO `vai_tro` (`ma_vai_tro`, `ten_hien_thi`) VALUES
('BAC_SI', 'Bác sĩ'),
('DUOC_SI', 'Dược sĩ'),
('KY_THUAT_VIEN_CHAN_DOAN_HINH_ANH', 'Kỹ thuật viên chẩn đoán hình ảnh'),
('KY_THUAT_VIEN_XET_NGHIEM', 'Kỹ thuật viên Xét nghiệm'),
('LE_TAN', 'Lễ tân'),
('NHAN_VIEN_KHO', 'Nhân viên kho'),
('QUAN_TRI_VIEN', 'Quản trị viên'),
('THU_NGAN', 'Thu ngân'),
('TRO_LY_BAC_SI_CHUYEN_KHOA', 'Trợ lý bác sĩ chuyên khoa'),
('TRO_LY_BAC_SI_TONG_QUAT', 'Trợ lý bác sĩ tổng quát');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bang_phan_cong_ca_lam`
--
ALTER TABLE `bang_phan_cong_ca_lam`
  ADD CONSTRAINT `fk_phancong_nhanvien` FOREIGN KEY (`ma_nhan_vien`) REFERENCES `nhan_vien` (`ma_nhan_vien`) ON DELETE CASCADE;

--
-- Constraints for table `chi_so_kham_tong_hop`
--
ALTER TABLE `chi_so_kham_tong_hop`
  ADD CONSTRAINT `FK_MaNVnhap` FOREIGN KEY (`ma_nhan_vien_nhap`) REFERENCES `nhan_vien` (`ma_nhan_vien`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_maphieukham` FOREIGN KEY (`ma_phieu_kham`) REFERENCES `phieu_kham` (`ma_phieu_kham`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `chi_tiet_chi_dinh`
--
ALTER TABLE `chi_tiet_chi_dinh`
  ADD CONSTRAINT `Fk_MaDV` FOREIGN KEY (`ma_dich_vu`) REFERENCES `dich_vu` (`ma_dich_vu`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Fk_maphieuchidinh` FOREIGN KEY (`ma_phieu_chi_dinh`) REFERENCES `phieu_chi_dinh` (`ma_phieu_chi_dinh`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `ct_hoa_don`
--
ALTER TABLE `ct_hoa_don`
  ADD CONSTRAINT `fk_cthoadon` FOREIGN KEY (`ma_hoa_don`) REFERENCES `hoa_don` (`ma_hoa_don`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `ct_phieu_nhap_thuoc`
--
ALTER TABLE `ct_phieu_nhap_thuoc`
  ADD CONSTRAINT `fk-maphieunhapthuoc` FOREIGN KEY (`ma_phieu_nhap_thuoc`) REFERENCES `phieu_nhap_thuoc` (`ma_phieu_nhap_thuoc`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `ct_toa_thuoc`
--
ALTER TABLE `ct_toa_thuoc`
  ADD CONSTRAINT `Fk_MaThuoc` FOREIGN KEY (`ma_thuoc`) REFERENCES `thuoc` (`ma_thuoc`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Fk_MaToaThuoc` FOREIGN KEY (`ma_toa_thuoc`) REFERENCES `toa_thuoc` (`ma_toa_thuoc`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `dang_ky_kham_benh`
--
ALTER TABLE `dang_ky_kham_benh`
  ADD CONSTRAINT `fk-mabn` FOREIGN KEY (`ma_benh_nhan`) REFERENCES `benh_nhan` (`ma_benh_nhan`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk-machuyenkhoa` FOREIGN KEY (`ma_chuyen_khoa`) REFERENCES `chuyen_khoa` (`ma_chuyen_khoa`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk-maphieukham` FOREIGN KEY (`ma_phieu_kham`) REFERENCES `phieu_kham` (`ma_phieu_kham`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_mactchidinh` FOREIGN KEY (`ma_chi_tiet_chi_dinh`) REFERENCES `chi_tiet_chi_dinh` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_manhanvien` FOREIGN KEY (`ma_nhan_vien`) REFERENCES `nhan_vien` (`ma_nhan_vien`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `dich_vu`
--
ALTER TABLE `dich_vu`
  ADD CONSTRAINT `fk-phongchucnang` FOREIGN KEY (`phong`) REFERENCES `phong_chuc_nang` (`ma_phong`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_machuyenkhoa12` FOREIGN KEY (`ma_chuyen_khoa`) REFERENCES `chuyen_khoa` (`ma_chuyen_khoa`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `hoa_don`
--
ALTER TABLE `hoa_don`
  ADD CONSTRAINT `fk-Manhanvien1` FOREIGN KEY (`ma_nhan_vien`) REFERENCES `nhan_vien` (`ma_nhan_vien`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk-mapk1` FOREIGN KEY (`ma_phieu_kham`) REFERENCES `phieu_kham` (`ma_phieu_kham`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `ket_qua_cdha`
--
ALTER TABLE `ket_qua_cdha`
  ADD CONSTRAINT `fk-mactchidinh1` FOREIGN KEY (`id_chi_tiet_chi_dinh`) REFERENCES `chi_tiet_chi_dinh` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk-manv2` FOREIGN KEY (`ma_bac_si_thuc_hien`) REFERENCES `nhan_vien` (`ma_nhan_vien`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk-manv3` FOREIGN KEY (`ma_nhan_vien_thuc_hien`) REFERENCES `nhan_vien` (`ma_nhan_vien`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `ket_qua_xet_nghiem`
--
ALTER TABLE `ket_qua_xet_nghiem`
  ADD CONSTRAINT `fk-mact1` FOREIGN KEY (`ma_chi_tiet_chi_dinh`) REFERENCES `chi_tiet_chi_dinh` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk-manv5` FOREIGN KEY (`nguoi_thuc_hien`) REFERENCES `nhan_vien` (`ma_nhan_vien`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk-manv6` FOREIGN KEY (`ma_bs_ket_luan`) REFERENCES `nhan_vien` (`ma_nhan_vien`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `kham_lam_sang`
--
ALTER TABLE `kham_lam_sang`
  ADD CONSTRAINT `fk-mapk5` FOREIGN KEY (`ma_phieu_kham`) REFERENCES `phieu_kham` (`ma_phieu_kham`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `kho_thuoc`
--
ALTER TABLE `kho_thuoc`
  ADD CONSTRAINT `fk-matthuoc1` FOREIGN KEY (`ma_thuoc`) REFERENCES `thuoc` (`ma_thuoc`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `lich_tai_kham`
--
ALTER TABLE `lich_tai_kham`
  ADD CONSTRAINT `Fk_maBN` FOREIGN KEY (`ma_benh_nhan`) REFERENCES `benh_nhan` (`ma_benh_nhan`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Fk_mack` FOREIGN KEY (`ma_chuyen_khoa`) REFERENCES `chuyen_khoa` (`ma_chuyen_khoa`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Fk_manv` FOREIGN KEY (`ma_nhan_vien`) REFERENCES `nhan_vien` (`ma_nhan_vien`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `Fk_mapk` FOREIGN KEY (`ma_phieu_kham`) REFERENCES `phieu_kham` (`ma_phieu_kham`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `phieu_chi_dinh`
--
ALTER TABLE `phieu_chi_dinh`
  ADD CONSTRAINT `fk-manvv1` FOREIGN KEY (`ma_nhan_vien_chi_dinh`) REFERENCES `nhan_vien` (`ma_nhan_vien`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk-mapkk1` FOREIGN KEY (`ma_phieu_kham`) REFERENCES `phieu_kham` (`ma_phieu_kham`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `phieu_chuyen_khoa`
--
ALTER TABLE `phieu_chuyen_khoa`
  ADD CONSTRAINT `fk_phieuchuyenkhoa_iddangkymoi` FOREIGN KEY (`id_dang_ky_moi`) REFERENCES `dang_ky_kham_benh` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_phieuchuyenkhoa_machuyenkhoaden` FOREIGN KEY (`ma_ck_den`) REFERENCES `chuyen_khoa` (`ma_chuyen_khoa`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_phieuchuyenkhoa_mackchuyentu` FOREIGN KEY (`ma_ck_tu`) REFERENCES `chuyen_khoa` (`ma_chuyen_khoa`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_phieuchuyenkhoa_manv` FOREIGN KEY (`ma_nhan_vien`) REFERENCES `nhan_vien` (`ma_nhan_vien`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_phieuchuyenkhoa_maphieukham` FOREIGN KEY (`ma_phieu_kham`) REFERENCES `phieu_kham` (`ma_phieu_kham`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `phieu_kham`
--
ALTER TABLE `phieu_kham`
  ADD CONSTRAINT `fk-mabn1` FOREIGN KEY (`ma_benh_nhan`) REFERENCES `benh_nhan` (`ma_benh_nhan`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk-manv88` FOREIGN KEY (`ma_nhan_vien`) REFERENCES `nhan_vien` (`ma_nhan_vien`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `phieu_nhap_thuoc`
--
ALTER TABLE `phieu_nhap_thuoc`
  ADD CONSTRAINT `fk-ncc` FOREIGN KEY (`ma_nha_cung_cap`) REFERENCES `nha_cung_cap` (`ma_nha_cung_cap`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_manvnhap1` FOREIGN KEY (`ma_nhan_vien_nhap`) REFERENCES `nhan_vien` (`ma_nhan_vien`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `phong_chuc_nang`
--
ALTER TABLE `phong_chuc_nang`
  ADD CONSTRAINT `fk_phong_chucvu` FOREIGN KEY (`ma_chuc_vu`) REFERENCES `chuc_vu` (`id`),
  ADD CONSTRAINT `fk_phong_chuyenkhoa` FOREIGN KEY (`ma_chuyen_khoa`) REFERENCES `chuyen_khoa` (`ma_chuyen_khoa`);

--
-- Constraints for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
  ADD CONSTRAINT `fk_nv_tk` FOREIGN KEY (`ma_nhan_vien`) REFERENCES `nhan_vien` (`ma_nhan_vien`) ON DELETE CASCADE ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_tk_vaitro` FOREIGN KEY (`vai_tro`) REFERENCES `vai_tro` (`ma_vai_tro`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `toa_thuoc`
--
ALTER TABLE `toa_thuoc`
  ADD CONSTRAINT `FK_PK_THUOC` FOREIGN KEY (`ma_phieu_kham`) REFERENCES `phieu_kham` (`ma_phieu_kham`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
