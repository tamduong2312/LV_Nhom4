-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 20, 2025 at 10:29 AM
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
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `bang_phan_cong_ca_lam`
--

INSERT INTO `bang_phan_cong_ca_lam` (`id`, `ma_nhan_vien`, `phong`, `gio_lam`, `gio_ket_thuc`, `thu`) VALUES
(46, 72, '901', '06:00:00', '06:00:00', 'Thứ 2'),
(47, 75, '123', '06:00:00', '06:00:00', 'Thứ 2'),
(48, 80, '323', '06:00:00', '06:00:00', 'Thứ 2'),
(49, 74, '989', '06:00:00', '06:00:00', 'Thứ 2'),
(50, 67, '123', '06:00:00', '05:00:00', 'Thứ 2'),
(51, 120, '123', '05:00:00', '04:00:00', 'Thứ 2'),
(52, 69, '121', '05:00:00', '00:00:00', 'Thứ 2'),
(53, 68, '333', '00:00:00', '00:00:00', 'Thứ 2'),
(54, 71, '', '00:00:00', '00:00:00', 'Thứ 2'),
(55, 70, '323', '00:00:00', '05:00:00', 'Thứ 2'),
(56, 65, '312', '00:00:00', '00:00:00', 'Thứ 2'),
(57, 108, '444', '00:00:00', '00:00:00', 'Thứ 2'),
(58, 73, '555', '00:00:00', '06:00:00', 'Thứ 2'),
(59, 77, '312', '00:00:00', '00:00:00', 'Thứ 2'),
(60, 79, '444', '00:00:00', '00:00:00', 'Thứ 2'),
(61, 78, '3123', '00:00:00', '06:00:00', 'Thứ 2'),
(62, 81, '312', '00:00:00', '06:00:00', 'Thứ 2'),
(63, 87, '312', '00:00:00', '00:00:00', 'Thứ 2');

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
  PRIMARY KEY (`ma_benh_nhan`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `benh_nhan`
--

INSERT INTO `benh_nhan` (`ma_benh_nhan`, `ho_ten`, `ngay_sinh`, `dia_chi`, `so_dien_thoai`, `email`, `nghe_nghiep`, `nhom_mau`, `di_ung_thuoc`, `nguoi_giam_ho`, `so_dien_thoai_nguoi_giam_ho`, `ghi_chu`, `cccd`, `gioi_tinh`) VALUES
(1, 'Trần Minh Khôi', '1992-03-14', '12 Nguyễn Văn Cừ, Q5, TP.HCM', '0909123123', 'khoi.tm@gmail.com', 'Kỹ sư phần mềm', 'O', 'Không', NULL, NULL, NULL, '12312321', b'01'),
(2, 'Nguyễn Thị Hồng', '1988-07-20', '78 Cách Mạng Tháng 8, TP.HCM', '0909555666', 'hongnt@gmail.com', 'Giáo viên', 'A', 'Penicillin', NULL, NULL, NULL, '123213213', b'00'),
(3, 'Lê Gia Bảo123', '2015-05-02', '45 Lê Lợi, Q1, TP.HCM', '909888777', '123123', 'Học sinh', 'B', '123123', '21312', '0', '23123', '0', b'00'),
(6, 'nguyễn thị tú', '2015-05-02', '45 Lê Lợi, Q1, TP.HCM', '909888777', '123123', 'Học sinh', 'B', '123123', '21312', '0', '23123', '0', b'00'),
(7, 'nguyễn thị hoa', '2015-05-02', '45 Lê Lợi, Q1, TP.HCM', '909888777', '123123', 'Học sinh', 'B', '123123', '21312', '0', '23123', '0', b'00'),
(8, 'Nguyễn Văn B', '2015-05-02', '45 Lê Lợi, Q1, TP.HCM', '909888777', '123123', 'Giáo viên', 'B', '123123', '', '0', '', '0', b'01'),
(9, 'Nguyễn Văn B', '2015-05-02', '45 Lê Lợi, Q1, TP.HCM', '909888777', '123123', 'Giáo viên', 'B', '123123', '', '0', '', '0', b'01'),
(10, 'Nguyễn Văn B', '2015-05-02', '45 Lê Lợi, Q1, TP.HCM', '909888777', '123123', 'Giáo viên', 'B', '123123', '', '123456789', '', '123456789123', b'01'),
(11, 'hao hao', '2015-05-02', '45 Lê Lợi, Q1, TP.HCM', '909888777', '123123', 'Giáo viên', 'B', '123123', '', '123456789', '', '123456789123', b'01'),
(12, 'hao hao', '2015-05-02', '45 Lê Lợi, Q1, TP.HCM', '909881257', '123123', 'Giáo viên', 'B', '123123', '', '123456789', '', '123456784789', b'01'),
(13, 'hao hao', '2015-05-02', '45 Lê Lợi, Q1, TP.HCM', '909881257', '123123', 'Giáo viên', 'B', '123123', '', '123456789', '', '123456784789', b'01'),
(14, 'taobao', '2015-05-02', '45 Lê Lợi, Q1, TP.HCM', '909881257', '123123', 'Giáo viên', 'B', '123123', '', '123456789', '', '123456784789', b'01'),
(15, 'taobao123', '2015-05-02', '45 Lê Lợi, Q1, TP.HCM', '909881257', '123123', 'Giáo viên', 'B', '123123', '', '123456789', '', '123456784789', b'01'),
(16, 'tam minh', '2015-05-02', '45 Lê Lợi, Q1, TP.HCM', '909881257', 'tamduonge7@gmail.com', 'Giáo viên', 'B', '123123', '', '123456789', '', '123456784789', b'01');

-- --------------------------------------------------------

--
-- Table structure for table `chi_so_nhi_khoa`
--

DROP TABLE IF EXISTS `chi_so_nhi_khoa`;
CREATE TABLE IF NOT EXISTS `chi_so_nhi_khoa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_phieu_kham` int NOT NULL,
  `can_nang` float DEFAULT NULL,
  `chieu_cao` float DEFAULT NULL,
  `nhiet_do` float DEFAULT NULL,
  `nhip_tim` int DEFAULT NULL,
  `nhip_tho` int DEFAULT NULL,
  `tinh_trang_dinh_duong` varchar(100) DEFAULT NULL,
  `tam_ly_hanh_vi` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `ghi_chu` text,
  `vong_dau` float DEFAULT NULL COMMENT 'Vòng đầu (cm) - Quan trọng với trẻ dưới 2 tuổi',
  `kham_tai_mui_hong` text COMMENT 'Mô tả họng, amidan, tai, mũi',
  `kham_ho_hap` text COMMENT 'Nghe phổi, tiếng rales, co kéo lồng ngực',
  `kham_da_niem_mac` text COMMENT 'Vàng da, ban đỏ, nốt phỏng (tay chân miệng)',
  `co_quan_khac` text COMMENT 'Bụng, thần kinh, vận động...',
  PRIMARY KEY (`id`),
  KEY `ma_phieu_kham` (`ma_phieu_kham`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `chi_so_nhi_khoa`
--

INSERT INTO `chi_so_nhi_khoa` (`id`, `ma_phieu_kham`, `can_nang`, `chieu_cao`, `nhiet_do`, `nhip_tim`, `nhip_tho`, `tinh_trang_dinh_duong`, `tam_ly_hanh_vi`, `ghi_chu`, `vong_dau`, `kham_tai_mui_hong`, `kham_ho_hap`, `kham_da_niem_mac`, `co_quan_khac`) VALUES
(4, 1, 18.5, 110, 37.3, 95, 24, 'Bình thường', 'Hoạt bát', 'Không vấn đề', NULL, NULL, NULL, NULL, NULL),
(5, 1, 16.2, 105, 38, 110, 28, 'Thiếu cân nhẹ', 'Quấy khóc', 'Nghi cảm cúm', NULL, NULL, NULL, NULL, NULL),
(6, 1, 20, 115, 36.9, 90, 22, 'Tốt', 'Hợp tác', 'Ổn định', NULL, NULL, NULL, NULL, NULL),
(7, 329, 3, 3, 3, 3, 3, '3', '3', '3', 3, '3', '3', '3', '3'),
(8, 350, 55, 55, 43, 55, 55, '5', '5', 'Viêm họng cấp', 0, '5', '5', '5', '5');

-- --------------------------------------------------------

--
-- Table structure for table `chi_so_noi_tong_quat`
--

DROP TABLE IF EXISTS `chi_so_noi_tong_quat`;
CREATE TABLE IF NOT EXISTS `chi_so_noi_tong_quat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_phieu_kham` int NOT NULL,
  `nhiet_do` float DEFAULT NULL,
  `huyet_ap_tam_thu` int DEFAULT NULL,
  `huyet_ap_tam_truong` int DEFAULT NULL,
  `nhip_tim` int DEFAULT NULL,
  `nhip_tho` int DEFAULT NULL,
  `can_nang` float DEFAULT NULL,
  `chieu_cao` float DEFAULT NULL,
  `ghi_chu` text,
  PRIMARY KEY (`id`),
  KEY `ma_phieu_kham` (`ma_phieu_kham`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `chi_so_noi_tong_quat`
--

INSERT INTO `chi_so_noi_tong_quat` (`id`, `ma_phieu_kham`, `nhiet_do`, `huyet_ap_tam_thu`, `huyet_ap_tam_truong`, `nhip_tim`, `nhip_tho`, `can_nang`, `chieu_cao`, `ghi_chu`) VALUES
(7, 11, 1, 1, 1, 1, 1, 1, 1, ''),
(8, 12, 1, 1, 1, 1, 1, 1, 1, ''),
(9, 15, 1, 1, 1, 1, 1, 1, 1, ''),
(10, 16, 1, 1, 1, 11, 1, 1, 1, ''),
(11, 18, 1, 1, 1, 1, 1, 1, 1, ''),
(12, 22, 1, 1, 1, 1, 1, 1, 1, ''),
(13, 23, 1, 1, 1, 1, 1, 1, 1, ''),
(14, 24, 1, 1, 1, 11, 1, 1, 1, ''),
(15, 25, 1, 1, 111, 1, 1, 1, 1, ''),
(16, 32, 34, 1, 1, 11, 1, 1, 1, ''),
(17, 40, 34, 11, 1, 1, 1, 1, 1, ''),
(18, 86, 34, 1, 2, 1, 1, 2, 1, ''),
(19, 121, 35, 2, 2, 2, 2, 35, 2, ''),
(20, 155, 35, 2, 2, 2, 2, 2, 2, ''),
(21, 164, 34, 2, 2, 2, 2, 2, 2, ''),
(22, 235, 34, 2, 2, 2, 2, 2, 2, ''),
(23, 238, 34, 2, 2, 2, 2, 2, 2, ''),
(24, 247, 34, 1, 1, 1, 1, 1, 1, ''),
(25, 268, 34, 22, 2, 2, 2, 2, 2, ''),
(26, 275, 34, 2, 2, 2, 2, 2, 2, ''),
(27, 283, 34, 2, 2, 2, 2, 2, 2, 'Chưa xác định được phiếu khám.'),
(28, 284, 34, 3, 2, 2, 3, 2, 2, ''),
(29, 286, 34, 3, 2, 2, 3, 2, 2, ''),
(30, 288, 34, 2, 2, 2, 2, 2, 2, 'Chưa xác định được phiếu khám.'),
(31, 289, 34, 2, 2, 2, 2, 2, 2, 'Chưa xác định được phiếu khám.'),
(32, 290, 34, 2, 2, 2, 2, 2, 2, 'Chưa xác định được phiếu khám.'),
(33, 291, 34, 3, 2, 2, 3, 2, 2, ''),
(34, 292, 34, 2, 2, 2, 2, 2, 2, 'Chưa xác định được phiếu khám.'),
(35, 293, 34, 2, 2, 2, 2, 2, 2, ''),
(36, 294, 34, 2, 2, 2, 2, 2, 2, ''),
(37, 298, 35, 2, 2, 2, 2, 2, 2, 'Chưa xác định được phiếu khám.'),
(38, 300, 34, 2, 2, 2, 2, 2, 2, 'Chưa xác định được phiếu khám.'),
(39, 301, 34, 2, 2, 2, 2, 2, 2, 'Chưa xác định được phiếu khám.'),
(43, 306, 34, 2, 2, 2, 2, 2, 2, ''),
(44, 307, 34, 2, 2, 2, 2, 2, 2, ''),
(45, 308, 35, 2, 2, 2, 2, 2, 2, ''),
(46, 309, 34, 2, 2, 2, 2, 2, 2, ''),
(47, 310, 34, 2, 2, 2, 2, 2, 2, ''),
(48, 315, 34, 2, 2, 2, 2, 2, 2, ''),
(49, 317, 34, 2, 2, 2, 2, 2, 2, ''),
(50, 323, 35, 3, 3, 3, 3, 3, 3, ''),
(51, 322, 34, 3, 3, 3, 3, 3, 3, ''),
(52, 322, 34, 3, 3, 3, 3, 3, 3, ''),
(53, 322, 34, 3, 3, 3, 3, 3, 3, ''),
(54, 323, 35, 3, 3, 3, 3, 3, 3, ''),
(55, 323, 35, 3, 3, 3, 3, 3, 3, ''),
(56, 323, 35, 3, 3, 3, 3, 3, 3, ''),
(57, 324, 34, 3, 3, 3, 3, 3, 3, ''),
(58, 326, 34, 6, 6, 6, 6, 6, 6, ''),
(59, 327, 36, 3, 3, 3, 3, 3, 3, ''),
(60, 328, 35, 2, 2, 2, 2, 2, 2, ''),
(61, 330, 34, 2, 2, 2, 2, 2, 2, ''),
(62, 329, 34, 3, 3, 3, 3, 3, 3, ''),
(63, 331, 34, 2, 2, 2, 2, 2, 2, ''),
(64, 332, 34, 3, 3, 3, 3, 3, 3, ''),
(65, 333, 35, 5, 5, 5, 5, 5, 5, ''),
(66, 334, 34, 3, 3, 3, 3, 3, 3, ''),
(67, 335, 35, 34, 3, 3, 3, 3, 3, ''),
(68, 336, 35, 2, 2, 2, 2, 2, 2, ''),
(69, 337, 34, 3, 3, 3, 3, 3, 3, ''),
(70, 338, 36, 50, 33, 33, 9, 3, 51, ''),
(71, 339, 35, 55, 35, 35, 35, 35, 55, ''),
(72, 340, 35, 55, 55, 55, 55, 55, 55, ''),
(73, 341, 36, 55, 55, 55, 55, 55, 55, ''),
(74, 341, 36, 55, 55, 55, 55, 55, 55, ''),
(75, 342, 41, 55, 55, 55, 55, 55, 55, ''),
(76, 343, 43, 55, 55, 55, 55, 55, 55, ''),
(77, 344, 43, 55, 55, 55, 55, 55, 55, ''),
(78, 345, 43, 55, 55, 55, 55, 55, 55, ''),
(79, 346, 41, 55, 55, 55, 55, 55, 55, ''),
(80, 347, 43, 55, 55, 55, 55, 55, 55, ''),
(81, 348, 43, 55, 55, 55, 55, 55, 55, ''),
(82, 349, 43, 55, 55, 55, 55, 55, 55, ''),
(83, 350, 43, 55, 55, 55, 55, 55, 55, ''),
(84, 350, 43, 55, 55, 55, 55, 55, 55, '');

-- --------------------------------------------------------

--
-- Table structure for table `chi_so_rang_ham_mat`
--

DROP TABLE IF EXISTS `chi_so_rang_ham_mat`;
CREATE TABLE IF NOT EXISTS `chi_so_rang_ham_mat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_phieu_kham` int NOT NULL,
  `tinh_trang_rang` text,
  `sau_rang` varchar(100) DEFAULT NULL,
  `cao_rang` varchar(50) DEFAULT NULL,
  `viem_nuou` varchar(50) DEFAULT NULL,
  `khop_can` varchar(50) DEFAULT NULL,
  `ghi_chu` text,
  `niem_mac_mieng` text COMMENT 'Lưỡi, má, sàn miệng (U, loét, nấm)',
  `do_lung_lay` varchar(50) DEFAULT NULL COMMENT 'Độ 1, 2, 3',
  `phu_hinh_cu` text COMMENT 'Đã bọc sứ, làm răng giả chưa',
  `benh_ly_khac` text COMMENT 'U nang, thắng lưỡi bám thấp...',
  PRIMARY KEY (`id`),
  KEY `ma_phieu_kham` (`ma_phieu_kham`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `chi_so_rang_ham_mat`
--

INSERT INTO `chi_so_rang_ham_mat` (`id`, `ma_phieu_kham`, `tinh_trang_rang`, `sau_rang`, `cao_rang`, `viem_nuou`, `khop_can`, `ghi_chu`, `niem_mac_mieng`, `do_lung_lay`, `phu_hinh_cu`, `benh_ly_khac`) VALUES
(4, 1, NULL, NULL, 'Ít', 'Không viêm', 'Bình thường', 'Răng tốt, chỉ cần vệ sinh định kỳ', NULL, NULL, NULL, NULL),
(5, 1, NULL, NULL, 'Trung bình', 'Viêm nhẹ', 'Lệch nhẹ', 'Khuyên trám và cạo vôi', NULL, NULL, NULL, NULL),
(6, 1, NULL, NULL, 'Nhiều', 'Viêm nặng', 'Lệch nhiều', 'Cần điều trị viêm nướu và cạo vôi', NULL, NULL, NULL, NULL),
(7, 1, NULL, NULL, 'Ít', 'Không viêm', 'Bình thường', 'Theo dõi định kỳ 6 tháng', NULL, NULL, NULL, NULL),
(8, 1, NULL, NULL, 'Nhiều', 'Viêm nhẹ', 'Bình thường', 'Cạo vôi + hướng dẫn vệ sinh', NULL, NULL, NULL, NULL),
(9, 1, NULL, NULL, 'Trung bình', 'Viêm nặng', 'Lệch nhẹ', 'Khuyên chỉnh nha', NULL, NULL, NULL, NULL),
(10, 1, NULL, NULL, 'Ít', 'Không viêm', 'Bình thường', 'Răng khỏe mạnh', NULL, NULL, NULL, NULL),
(11, 1, NULL, NULL, 'Nhiều', 'Viêm nặng', 'Lệch nhiều', 'Cần điều trị nha chu', NULL, NULL, NULL, NULL),
(12, 1, NULL, NULL, 'Trung bình', 'Viêm nhẹ', 'Bình thường', 'Cạo vôi và theo dõi', NULL, NULL, NULL, NULL),
(13, 1, NULL, NULL, 'Ít', 'Không viêm', 'Bình thường', 'Không vấn đề gì', NULL, NULL, NULL, NULL),
(14, 333, '3', '3', '3', '3', '3', '3', '3', '3', '3', '3');

-- --------------------------------------------------------

--
-- Table structure for table `chi_so_tai_mui_hong`
--

DROP TABLE IF EXISTS `chi_so_tai_mui_hong`;
CREATE TABLE IF NOT EXISTS `chi_so_tai_mui_hong` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_phieu_kham` int NOT NULL,
  `thinh_luc_tai_trai` varchar(50) DEFAULT NULL,
  `thinh_luc_tai_phai` varchar(50) DEFAULT NULL,
  `tinh_trang_mui` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `tinh_trang_hong` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `soi_tai_mui_hong` text,
  `ghi_chu` text,
  `ong_tai` text COMMENT 'Nấm, dị vật, ráy tai',
  `mang_nhi_phai` varchar(255) DEFAULT NULL COMMENT 'Sáng, thủng, nón sáng...',
  `mang_nhi_trai` varchar(255) DEFAULT NULL COMMENT 'Sáng, thủng, nón sáng...',
  `vach_ngan` varchar(255) DEFAULT NULL COMMENT 'Thẳng, vẹo trái/phải',
  `cuon_mui` varchar(255) DEFAULT NULL COMMENT 'Nề, quá phát, nhợt màu',
  `khe_mui` varchar(255) DEFAULT NULL COMMENT 'Sạch, có mủ, polyp',
  `amidan` varchar(255) DEFAULT NULL COMMENT 'Độ 1-4, hốc mủ, xơ teo',
  `thanh_quan` text COMMENT 'Dây thanh, sụn phễu (hạt xơ, polyp)',
  PRIMARY KEY (`id`),
  KEY `ma_phieu_kham` (`ma_phieu_kham`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `chi_so_tai_mui_hong`
--

INSERT INTO `chi_so_tai_mui_hong` (`id`, `ma_phieu_kham`, `thinh_luc_tai_trai`, `thinh_luc_tai_phai`, `tinh_trang_mui`, `tinh_trang_hong`, `soi_tai_mui_hong`, `ghi_chu`, `ong_tai`, `mang_nhi_phai`, `mang_nhi_trai`, `vach_ngan`, `cuon_mui`, `khe_mui`, `amidan`, `thanh_quan`) VALUES
(1, 1, 'Bình thường', 'Bình thường', 'Nghẹt nhẹ', 'Hơi đỏ', 'Không mủ', 'Viêm nhẹ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 1, 'Giảm nhẹ', 'Bình thường', 'Viêm', 'Sưng đỏ', 'Có mủ', 'Cần theo dõi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 1, 'Bình thường', 'Bình thường', 'Thông thoáng', 'Ổn', 'Không bất thường', 'OK', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 336, '4', '4', '4', '4', '', '4', '4', '', '', '34', '4', '44', '4', '4');

-- --------------------------------------------------------

--
-- Table structure for table `chi_so_tim_mach`
--

DROP TABLE IF EXISTS `chi_so_tim_mach`;
CREATE TABLE IF NOT EXISTS `chi_so_tim_mach` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_phieu_kham` int NOT NULL,
  `nhip_tim` int DEFAULT NULL,
  `spo2` float DEFAULT NULL,
  `huyet_ap_tam_thu` int DEFAULT NULL,
  `huyet_ap_tam_truong` int DEFAULT NULL,
  `cholesterol` float DEFAULT NULL,
  `hdl_cholesterol` float DEFAULT NULL,
  `ldl_cholesterol` float DEFAULT NULL,
  `triglyceride` float DEFAULT NULL,
  `duong_huyet` float DEFAULT NULL,
  `ecg_ket_qua` text,
  `sieu_am_tim` text,
  `ghi_chu` text,
  PRIMARY KEY (`id`),
  KEY `ma_phieu_kham` (`ma_phieu_kham`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `chi_so_tim_mach`
--

INSERT INTO `chi_so_tim_mach` (`id`, `ma_phieu_kham`, `nhip_tim`, `spo2`, `huyet_ap_tam_thu`, `huyet_ap_tam_truong`, `cholesterol`, `hdl_cholesterol`, `ldl_cholesterol`, `triglyceride`, `duong_huyet`, `ecg_ket_qua`, `sieu_am_tim`, `ghi_chu`) VALUES
(1, 1, 78, NULL, 120, 80, 180, NULL, NULL, 140, 95, 'Nhịp xoang bình thường', NULL, 'Không vấn đề'),
(2, 1, 95, NULL, 145, 90, 220, NULL, NULL, 180, 120, 'Rối loạn nhịp nhẹ', NULL, 'Cảnh báo sớm'),
(3, 1, 70, NULL, 110, 75, 160, NULL, NULL, 110, 85, 'Bình thường', NULL, 'Ổn');

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
  PRIMARY KEY (`id`),
  KEY `fk-maphieuchidinh` (`ma_phieu_chi_dinh`),
  KEY `fk-madv` (`ma_dich_vu`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `chi_tiet_chi_dinh`
--

INSERT INTO `chi_tiet_chi_dinh` (`id`, `ma_phieu_chi_dinh`, `ma_dich_vu`, `so_luong`, `don_gia`, `trang_thai_dv`) VALUES
(28, 14, 15, 1, 150000.00, 'DA_CO_KET_QUA'),
(29, 15, 17, 1, 300000.00, 'CHUA_THUC_HIEN'),
(32, 18, 15, 1, 150000.00, 'DA_CO_KET_QUA'),
(33, 18, 9, 1, 150000.00, 'DA_CO_KET_QUA'),
(34, 19, 9, 1, 150000.00, 'DA_CO_KET_QUA'),
(35, 19, 15, 1, 150000.00, 'DA_CO_KET_QUA'),
(36, 20, 13, 1, 60000.00, 'CHUA_THUC_HIEN'),
(37, 20, 31, 1, 50000.00, 'CHUA_THUC_HIEN'),
(38, 21, 15, 1, 150000.00, 'DA_CO_KET_QUA'),
(39, 22, 1, 1, 100000.00, 'CHUA_THUC_HIEN'),
(40, 23, 16, 1, 150000.00, 'DA_CO_KET_QUA'),
(41, 23, 9, 1, 150000.00, 'CHUA_THUC_HIEN'),
(42, 24, 9, 1, 150000.00, 'DA_CO_KET_QUA'),
(43, 25, 18, 1, 120000.00, 'DA_CO_KET_QUA'),
(44, 26, 7, 1, 80000.00, 'CHUA_THUC_HIEN'),
(45, 27, 31, 1, 50000.00, 'CHUA_THUC_HIEN'),
(46, 28, 18, 1, 120000.00, 'DA_CO_KET_QUA'),
(47, 29, 9, 1, 150000.00, 'CHUA_THUC_HIEN'),
(48, 30, 11, 1, 80000.00, 'CHUA_THUC_HIEN'),
(49, 31, 20, 1, 100000.00, 'CHUA_THUC_HIEN'),
(50, 32, 12, 1, 50000.00, 'CHUA_THUC_HIEN'),
(51, 33, 9, 1, 150000.00, 'CHUA_THUC_HIEN'),
(52, 34, 7, 1, 80000.00, 'CHUA_THUC_HIEN'),
(53, 35, 9, 1, 150000.00, 'CHUA_THUC_HIEN'),
(54, 36, 9, 1, 150000.00, 'CHUA_THUC_HIEN');

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `chuc_vu`
--

INSERT INTO `chuc_vu` (`id`, `ten_chuc_vu`) VALUES
(12, 'Bác sĩ Chẩn đoán hình ảnh'),
(2, 'Bác sĩ chuyên khoa'),
(1, 'Bác sĩ tổng quát'),
(5, 'Dược sĩ'),
(11, 'Kỹ thuật viên Xét nghiệm'),
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
  PRIMARY KEY (`ma_chuyen_khoa`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `chuyen_khoa`
--

INSERT INTO `chuyen_khoa` (`ma_chuyen_khoa`, `ten_chuyen_khoa`, `mo_ta`) VALUES
(1, 'Nội tổng quát', 'Khám và điều trị các bệnh nội khoa thông thường'),
(3, 'Nhi khoa', 'Khám và điều trị bệnh cho trẻ em'),
(4, 'Tai - Mũi - Họng', 'Khám và điều trị bệnh lý tai, mũi, họng'),
(5, 'Răng - Hàm - Mặt', 'Khám, nhổ, trám và phục hình răng'),
(7, 'Xét nghiệm', 'Thực hiện các xét nghiệm máu, nước tiểu, sinh hóa...'),
(11, 'Tim mạch', 'kiểm tra về tim'),
(12, 'Chẩn đoán hình ảnh', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `ct_hoa_don`
--

INSERT INTO `ct_hoa_don` (`id`, `ma_hoa_don`, `noi_dung`, `loai_muc`, `id_goc`, `so_luong`, `don_gia`, `thanh_tien`) VALUES
(19, 34, 'Khám Nội tổng quát', 'DICH_VU', 1, 1, 100000.00, 100000.00),
(20, 34, 'Tổng phân tích tế bào máu (CBC)', 'DICH_VU', 7, 1, 80000.00, 80000.00),
(21, 34, 'Efferalgan 500mg', 'THUOC', 10, 4, 6000.00, 24000.00),
(22, 34, 'Berberin Mộc Hương', 'THUOC', 7, 4, 50000.00, 200000.00),
(23, 36, 'Khám Nội tổng quát', 'DICH_VU', 1, 1, 100000.00, 100000.00),
(24, 36, 'Bộ mỡ máu (Cholesterol, Triglyceride...)', 'DICH_VU', 9, 1, 150000.00, 150000.00),
(25, 36, 'Vitamin C 1000mg11', 'THUOC', 2, 4, 70010.00, 280040.00),
(26, 38, 'Khám Nội tổng quát', 'DICH_VU', 1, 1, 100000.00, 100000.00),
(27, 38, 'Panadol Extra', 'THUOC', 5, 16, 15000.00, 240000.00),
(28, 38, 'Eugica Fort', 'THUOC', 9, 20, 80000.00, 1600000.00),
(29, 39, 'Khám Nội tổng quát', 'DICH_VU', 1, 1, 100000.00, 100000.00),
(30, 39, 'Panadol Extra', 'THUOC', 5, 16, 15000.00, 240000.00),
(31, 39, 'Eugica Fort', 'THUOC', 9, 20, 80000.00, 1600000.00),
(32, 40, 'Khám Nội tổng quát', 'DICH_VU', 1, 1, 100000.00, 100000.00),
(33, 40, 'Panadol Extra', 'THUOC', 5, 16, 15000.00, 240000.00),
(34, 40, 'Eugica Fort', 'THUOC', 9, 20, 80000.00, 1600000.00),
(35, 41, 'Khám Nội tổng quát', 'DICH_VU', 1, 1, 100000.00, 100000.00),
(36, 41, 'Khám Nhi khoa', 'DICH_VU', 2, 1, 100000.00, 100000.00),
(37, 41, 'Khám Cấp cứu / Ngoài giờ', 'DICH_VU', 6, 1, 200000.00, 200000.00),
(38, 41, 'Bộ mỡ máu (Cholesterol, Triglyceride...)', 'DICH_VU', 9, 1, 150000.00, 150000.00),
(39, 41, 'Smecta', 'THUOC', 8, 10, 4000.00, 40000.00);

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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
(32, 20, 13, 10, 70000.00, 700000.00);

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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `ct_toa_thuoc`
--

INSERT INTO `ct_toa_thuoc` (`id`, `ma_toa_thuoc`, `ma_thuoc`, `lieu_dung`, `sang`, `trua`, `chieu`, `toi`, `so_ngay`, `cach_dung`, `thoi_diem_dung`) VALUES
(12, 6, 1, NULL, '1', '1', '1', '1', 2, '', 'Sau khi ăn'),
(18, 6, 7, NULL, '2', '12', '12', '2', 2, 'không được ăn hải sản , uống nhiều nước', 'Sau khi ăn'),
(21, 7, 8, NULL, '1', '1', '1', '1', 1, 'Sau khi ăn. ', 'Sau khi ăn'),
(22, 7, 3, NULL, '1', '1', '1', '1', 1, 'Sau khi ăn. ', 'Sau khi ăn'),
(23, 9, 1, NULL, '1', '1', '1', '1', 1, 'Sau khi ăn. ', 'Sau khi ăn'),
(24, 10, 8, NULL, '1', '1', '1', '1', 1, 'Sau khi ăn. ', 'Sau khi ăn'),
(25, 11, 1, NULL, '1', '1', '1', '1', 1, 'Sau khi ăn. ', 'Sau khi ăn'),
(26, 12, 12, NULL, '1', '1', '1', '1', 1, 'Sau khi ăn. ', 'Sau khi ăn'),
(27, 13, 14, NULL, '1', '1', '1', '1', 1, 'Sau khi ăn. ', 'Sau khi ăn'),
(28, 14, 1, NULL, '1', '1', '1', '1', 1, 'Sau khi ăn. ', 'Sau khi ăn'),
(29, 15, 2, NULL, '1', '', '', '', 1, 'Sau khi ăn. ', 'Sau khi ăn'),
(30, 16, 14, NULL, '1', '1', '', '', 5, 'Sau khi ăn. ', 'Sau khi ăn'),
(31, 15, 7, NULL, '1', '1', '1', '1', 5, 'Sau khi ăn. ', 'Sau khi ăn'),
(32, 18, 2, NULL, '1', '', '', '', 1, 'Sau khi ăn. ', 'Sau khi ăn'),
(33, 18, 7, NULL, '1', '1', '1', '1', 5, 'Sau khi ăn. ', 'Sau khi ăn'),
(34, 18, 12, NULL, '1', '1', '1', '1', 1, 'Sau khi ăn. ', 'Sau khi ăn'),
(39, 19, 3, NULL, '1', '1', '1', '', 5, 'Sau khi ăn. ', 'Sau khi ăn'),
(42, 20, 7, NULL, '1', '1', '', '', 5, 'Sau khi ăn. ', 'Sau khi ăn'),
(43, 21, 11, NULL, '1', '1', '', '', 5, 'Sau khi ăn. ', 'Sau khi ăn'),
(44, 22, 11, NULL, '1', '1', '1', '1', 1, 'Sau khi ăn. uống sau khi ăn , sốt thì uống thuốc', 'Sau khi ăn'),
(45, 23, 3, NULL, '1', '1', '', '', 5, 'Sau khi ăn. ', 'Sau khi ăn'),
(46, 24, 14, NULL, '1', '1', '', '', 5, 'Sau khi ăn. ', 'Sau khi ăn'),
(47, 25, 10, NULL, '1', '1', '1', '1', 1, 'Sau khi ăn. ', 'Sau khi ăn'),
(48, 25, 7, NULL, '1', '1', '1', '1', 1, 'Sau khi ăn. ', 'Sau khi ăn'),
(49, 26, 2, NULL, '1', '1', '1', '1', 1, 'Sau khi ăn. ', 'Sau khi ăn'),
(51, 28, 5, NULL, '1', '1', '1', '1', 4, 'khong được tắm vào buổi tối', 'Trước khi ăn'),
(52, 28, 9, NULL, '1', '1', '1', '1', 5, 'phải ăn uống đầy đủ', 'Sau khi ăn'),
(53, 29, 8, NULL, '1', '1', '', '', 5, '', 'Sau khi ăn');

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
  `trang_thai` enum('CHO_KHAM','DANG_KHAM','VANG_MAT','CHO_KHAM_BS','DA_KHAM','HUY') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'CHO_KHAM',
  `ghi_chu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dkbn_benhnhan` (`ma_benh_nhan`),
  KEY `fk_dkbn_nhanvien` (`ma_nhan_vien`),
  KEY `fk_dkbn_chuyenkhoa` (`ma_chuyen_khoa`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `dang_ky_kham_benh`
--

INSERT INTO `dang_ky_kham_benh` (`id`, `ma_benh_nhan`, `ma_nhan_vien`, `ma_chuyen_khoa`, `so_thu_tu`, `thoi_gian_dang_ky`, `trang_thai`, `ghi_chu`) VALUES
(72, 2, 72, 1, 1, '2025-12-10 20:10:32', 'DA_KHAM', ''),
(73, 2, 72, 1, 1, '2025-12-11 20:12:45', 'CHO_KHAM_BS', ''),
(74, 2, 72, 1, 2, '2025-12-10 14:12:49', 'CHO_KHAM', ''),
(75, 1, 72, 3, 3, '2025-12-10 14:14:18', 'DA_KHAM', ''),
(76, 2, 72, 1, 3, '2025-12-10 15:38:49', 'CHO_KHAM', ''),
(77, 12, 72, 1, 4, '2025-12-10 15:40:24', 'CHO_KHAM_BS', ''),
(78, 6, 72, 3, 2, '2025-12-10 21:18:00', 'CHO_KHAM_BS', ''),
(79, 1, 72, 3, 1, '2025-12-11 21:23:21', 'CHO_KHAM_BS', ''),
(80, 6, 72, 5, 1, '2025-12-11 22:34:16', 'CHO_KHAM_BS', ''),
(81, 11, 72, 1, 2, '2025-12-11 23:04:43', 'CHO_KHAM_BS', ''),
(82, 2, 72, 1, 1, '2025-12-12 11:01:22', 'CHO_KHAM_BS', ''),
(83, 14, 72, 4, 1, '2025-12-11 22:32:49', 'CHO_KHAM_BS', ''),
(84, 15, 72, 11, 1, '2025-12-11 23:18:38', 'CHO_KHAM_BS', ''),
(85, 6, 72, 1, 2, '2025-12-12 14:30:34', 'DA_KHAM', ''),
(86, 14, 72, 1, 3, '2025-12-12 15:40:37', 'DA_KHAM', ''),
(87, 12, 72, 1, 4, '2025-12-12 20:39:34', 'DA_KHAM', ''),
(88, 3, 72, 1, 5, '2025-12-12 22:11:44', 'DA_KHAM', ''),
(89, 15, 72, 1, 1, '2025-12-13 18:52:16', 'DA_KHAM', ''),
(90, 6, 72, 1, 2, '2025-12-13 19:11:52', 'DA_KHAM', ''),
(91, 10, 72, 1, 3, '2025-12-13 23:01:41', 'DA_KHAM', ''),
(92, 15, 72, 1, 1, '2025-12-14 14:15:55', 'DA_KHAM', ''),
(93, 1, 72, 1, 1, '2025-12-15 00:54:11', 'DA_KHAM', ''),
(94, 2, 72, 1, 2, '2025-12-15 01:20:33', 'CHO_KHAM', ''),
(95, 16, 72, 1, 3, '2025-12-15 01:21:04', 'CHO_KHAM_BS', ''),
(96, 6, 72, 1, 4, '2025-12-15 02:06:49', 'CHO_KHAM', ''),
(97, 7, 72, 1, 5, '2025-12-15 02:07:13', 'CHO_KHAM', ''),
(98, 16, 72, 1, 1, '2025-12-16 00:17:06', 'DA_KHAM', ''),
(99, 11, 72, 1, 1, '2025-12-19 12:39:20', 'CHO_KHAM_BS', ''),
(100, 16, 72, 1, 1, '2025-12-20 16:34:21', 'DA_KHAM', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
(6, 'Khám Cấp cứu / Ngoài giờ', 200000.00, 'KHAM_BENH', 1, 1),
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
(31, 'Chụp X-quang răng (tại ghế)', 50000.00, 'CLS_CHAN_DOAN_HINH_ANH', 7, 12),
(36, 'a', 11.00, 'CLS_CHAN_DOAN_HINH_ANH', 2, 11),
(37, 'ff', 222.00, 'CLS_XET_NGHIEM', 2, 4);

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
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hoa_don`
--

INSERT INTO `hoa_don` (`ma_hoa_don`, `ma_phieu_kham`, `ma_nhan_vien`, `tong_tien`, `ngay_thanh_toan`, `ghi_chu`, `trang_thai`) VALUES
(33, 345, 73, 0.00, '2025-12-14 22:37:23', NULL, 'chưa thanh toán'),
(34, 345, 73, 404000.00, '2025-12-14 22:42:08', NULL, 'đã thanh toán'),
(35, 345, 73, 0.00, '2025-12-14 22:42:18', NULL, 'chưa thanh toán'),
(36, 346, 73, 530040.00, '2025-12-15 00:55:17', NULL, 'đã thanh toán'),
(37, 348, 73, 0.00, '2025-12-16 00:47:50', NULL, 'chưa thanh toán'),
(38, 348, 73, 1940000.00, '2025-12-16 00:49:34', NULL, 'đã thanh toán'),
(39, 348, 73, 1940000.00, '2025-12-16 02:30:39', NULL, 'đã thanh toán'),
(40, 348, 73, 1940000.00, '2025-12-16 02:49:59', NULL, 'đã thanh toán'),
(41, 350, 73, 590000.00, '2025-12-20 16:57:22', NULL, 'đã thanh toán');

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
  `duong_dan_anh_1` varchar(255) DEFAULT NULL,
  `duong_dan_anh_2` varchar(255) DEFAULT NULL,
  `ngay_thuc_hien` datetime DEFAULT CURRENT_TIMESTAMP,
  `ma_bac_si_thuc_hien` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_MACT` (`id_chi_tiet_chi_dinh`),
  KEY `fk_mabs` (`ma_bac_si_thuc_hien`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `ket_qua_cdha`
--

INSERT INTO `ket_qua_cdha` (`id`, `id_chi_tiet_chi_dinh`, `mo_ta_hinh_anh`, `ket_luan`, `de_nghi`, `duong_dan_anh_1`, `duong_dan_anh_2`, `ngay_thuc_hien`, `ma_bac_si_thuc_hien`) VALUES
(3, 28, '- Hình ảnh cấu trúc giải phẫu bình thường.\n- Nhu mô đồng nhất, không thấy khối khu trú.\n- Không thấy dịch tự do.', 'Hiện tại chưa phát hiện bất thường trên hình ảnh.', '', NULL, NULL, '2025-12-06 21:59:55', 120),
(4, 32, '- Hình ảnh cấu trúc giải phẫu bình thường.\n- Nhu mô đồng nhất, không thấy khối khu trú.\n- Không thấy dịch tự do.', 'Hiện tại chưa phát hiện bất thường trên hình ảnh.', '', NULL, NULL, '2025-12-08 22:05:04', 120),
(5, 35, '- Hình ảnh cấu trúc giải phẫu bình thường.\n- Nhu mô đồng nhất, không thấy khối khu trú.\n- Không thấy dịch tự do.', 'Hiện tại chưa phát hiện bất thường trên hình ảnh.', '', NULL, NULL, '2025-12-09 01:00:39', 120),
(6, 38, '- Hình ảnh cấu trúc giải phẫu bình thường.\n- Nhu mô đồng nhất, không thấy khối khu trú.\n- Không thấy dịch tự do.', 'Hiện tại chưa phát hiện bất thường trên hình ảnh.', '', 'src/Imaginc20a69e9-9679-4c4d-83c2-5aaa71d75820.png', NULL, '2025-12-09 10:43:14', 120),
(7, 40, '- Hình ảnh cấu trúc giải phẫu bình thường.\n- Nhu mô đồng nhất, không thấy khối khu trú.\n- Không thấy dịch tự do.', 'Hiện tại chưa phát hiện bất thường trên hình ảnh.', '', NULL, NULL, '2025-12-09 19:44:28', 120),
(8, 43, '- Hình ảnh cấu trúc giải phẫu bình thường.\n- Nhu mô đồng nhất, không thấy khối khu trú.\n- Không thấy dịch tự do.', 'Hiện tại chưa phát hiện bất thường trên hình ảnh.', '', NULL, NULL, '2025-12-10 20:11:56', 120),
(9, 46, '- Hình ảnh cấu trúc giải phẫu bình thường.\n- Nhu mô đồng nhất, không thấy khối khu trú.\n- Không thấy dịch tự do.', 'Hiện tại chưa phát hiện bất thường trên hình ảnh.', '', 'src/Imagin/b2363e57-d516-49b4-b4d3-7d96d54b3da3.png', NULL, '2025-12-11 20:33:30', 120);

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
  KEY `Fk_mactchidinh` (`ma_chi_tiet_chi_dinh`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `ket_qua_xet_nghiem`
--

INSERT INTO `ket_qua_xet_nghiem` (`id`, `ma_chi_tiet_chi_dinh`, `ngay_thuc_hien`, `nguoi_thuc_hien`, `ket_luan`, `hong_cau`, `bach_cau`, `tieu_cau`, `huyet_sac_to`, `hematocrit`, `nhom_mau`, `toc_do_mau_lang`, `dong_mau_co_ban`, `glucose`, `hbA1c`, `ure`, `creatinine`, `ast_got`, `alt_gpt`, `ggt`, `cholesterol_tp`, `triglyceride`, `hdl_c`, `ldl_c`, `acid_uric`, `bilirubin_tp`, `ty_trong`, `ph`, `bach_cau_nuoc_tieu`, `hong_cau_nuoc_tieu`, `protein_nuoc_tieu`, `duong_nuoc_tieu`, `nitrit`, `ketone`, `hbsag`, `hcv_ab`, `hiv_ab`, `tpha_syphilis`, `crp_dinh_luong`, `rf_dinh_luong`, `ghi_chu_them`) VALUES
(7, 33, '2025-12-08 22:10:13', 108, '2', 1, 1, 1, 1, 1, '1', 1, '1', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '', '', '', '', '', '', '', '', '', '', 0, 0, '2'),
(8, 34, '2025-12-09 08:39:07', 108, '2', 2, 2, 2, 2, 2, '2', 2, '2', 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, '', '', '', '', '', '', '', '', '', '', 0, 0, '2'),
(9, 42, '2025-12-10 20:08:49', 108, '3', 3, 3, 3, 3, 3, '3', 3, '3', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '', '', '', '', '', '', '', '', '', '', 0, 0, '3');

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `kham_lam_sang`
--

INSERT INTO `kham_lam_sang` (`id`, `ma_phieu_kham`, `ly_do_kham`, `tien_su_ban_than`, `benh_su`, `chan_doan_so_bo`, `loi_dan_bac_si`, `ket_qua_kham_can_lam_sang`, `kham_lam_sang`) VALUES
(1, 121, 'a', 'a', 'a', 'Cơn đau thắt ngực', 'a', '- CĐ Hình ảnh: bất thường', 'a'),
(3, 247, 'c', 'c', 'c', 'c', 'c', '', 'c'),
(4, 303, 'đau bụng', 'hay ho', 'đến tối hay đau bụng', 'cc', 'cc', '- CĐHA: Hiện tại chưa phát hiện bất thường trên hình ảnh.\n- XN: 2', 'bụng hay đau ở vùng giữa'),
(5, 307, 'a', 'a', 'a', 'a', 'a', '- XN: 2', 'a'),
(6, 308, 'b', 'b', 'b', '', '', '', 'b'),
(7, 311, 'đau bụng', '', '', '', '', '', ''),
(8, 323, 'cc', 'cc', 'cc', '', '', '', 'cc'),
(9, 324, 'aa', 'bb', 'aa', '', '', '', 'bb'),
(10, 326, 'bb', 'bb', 'bb', 'bb', 'bb', '- CÐHA: Hiện tại chưa phát hiện bất thường trên hình ảnh.', 'bb'),
(11, 327, 'vvvv', 'vv', 'vvv', '', '', '', 'vv'),
(12, 328, 'cc1', 'cc1', 'cc1', '', '', '', 'cc'),
(13, 329, 'ff', 'ff', 'ff', '3', '3', '', 'Xem chi tiết tại phiếu chuyên khoa Nhi'),
(14, 332, 'c', 'c', 'c', '', '', '', ''),
(15, 333, 'ss', 'ss', 'ss', '3', '', '', ''),
(16, 334, 'cc', 'cc', 'cc1', '', '', '', ''),
(17, 336, '33', '33', '33', '4', '', '', ''),
(18, 339, 'f', 'f', 'f', '', '', '', 'f'),
(19, 340, 'v', 'v', 'v', 'Viêm amidan cấp', 'v', '', 'v'),
(20, 341, 'b', 'b', 'b', 'bb', 'b', '', 'b'),
(21, 342, 'c', 'c', 'c', 'Viêm phế quản cấp', 'c', '', 'c'),
(22, 343, 'g', 'g', 'g', 'Viêm họng cấp', 'g', '', 'g'),
(23, 344, 'c', 'c', 'c', 'Viêm amidan cấp', 'c', '', 'c'),
(24, 345, 'c', 'c', 'c', 'Viêm họng cấp', 'c', '', 'c'),
(25, 346, 's', 's', 's', 's', 's', '', 's'),
(26, 350, 'c', 'c', 'c', 'c', 'c', '', 'Xem chi tiết tại phiếu chuyên khoa Nhi');

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
(1, 1, 5515, '2025-11-24 22:13:27'),
(2, 3, 1110, '2025-11-24 23:17:55'),
(3, 2, 5, '2025-11-20 20:39:34'),
(5, 5, 272, '2025-12-16 02:46:35'),
(6, 6, 20, '2025-12-06 14:58:27'),
(7, 7, 200, '2025-12-06 14:58:27'),
(8, 8, 10, '2025-12-06 14:58:27'),
(9, 9, 3840, '2025-12-16 02:46:35'),
(10, 10, 100, '2025-12-06 14:58:27'),
(11, 11, 5, '2025-12-06 14:58:27'),
(12, 12, 60, '2025-12-06 14:58:27'),
(13, 13, 10, '2025-12-06 14:58:27');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `lich_tai_kham`
--

INSERT INTO `lich_tai_kham` (`id`, `ma_benh_nhan`, `ma_chuyen_khoa`, `ma_phieu_kham`, `ma_nhan_vien`, `ngay_tai_kham`, `ghi_chu`, `trang_thai`, `da_gui_thong_bao`) VALUES
(2, 11, 1, 304, 65, '2025-12-27', '', 'CHUA_DEN', 0),
(3, 10, 1, 344, 65, '2025-12-31', 'cc', 'CHUA_DEN', 0),
(4, 15, 1, 345, 65, '2025-12-31', '', 'CHUA_DEN', 0),
(6, 16, 1, 347, 65, '2025-12-16', '', 'CHUA_DEN', 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `nhan_vien`
--

INSERT INTO `nhan_vien` (`ma_nhan_vien`, `ho_ten`, `gioi_tinh`, `ngay_sinh`, `dia_chi`, `so_dien_thoai`, `email`, `chuyen_khoa`, `bang_cap`, `chuc_vu`, `ngay_vao_lam`, `cccd`) VALUES
(65, 'thanh trúc', b'01', '2000-11-28', 'HCM', '123456782', 'bstq@gmail.com', 'Nội tổng quát', 'đại học bách khoa', 'Bác sĩ tổng quát', '2025-11-29', '123456789124'),
(67, 'thanh trúc', b'01', '2000-11-28', 'HCM', '123456783', 'nhikhoa@gmail.com', 'Nhi khoa', 'đại học bách khoa', 'Bác sĩ chuyên khoa', '2025-11-29', '123456789125'),
(68, 'thanh trúc', b'01', '2000-11-28', 'HCM', '123456784', 'taimuihong@gmail.com', 'Tai - Mũi - Họng', 'đại học bách khoa', 'Bác sĩ chuyên khoa', '2025-11-29', '123456789126'),
(69, 'thanh trúc', b'01', '2000-11-28', 'HCM', '123456785', 'ranghammat@gmail.com', 'Răng - Hàm - Mặt', 'đại học bách khoa', 'Bác sĩ chuyên khoa', '2025-11-29', '123456789111'),
(70, 'thanh trúc', b'01', '2000-11-28', 'HCM', '123456786', 'xetnghiem@gmail.com', 'Xét nghiệm', 'đại học bách khoa', 'Bác sĩ chuyên khoa', '2025-11-29', '123456789321'),
(71, 'thanh trúc', b'01', '2000-11-28', 'HCM', '223456789', 'timmach@gmail.com', 'Tim mạch', 'đại học bách khoa', 'Bác sĩ chuyên khoa', '2025-11-29', '123456789129'),
(72, 'thanh hà', b'01', '2000-11-28', 'HCM', '233456789', 'letan@gmail.com', NULL, 'đại học bách khoa', 'Lễ tân', '2025-11-29', '1763456789123'),
(73, 'thành minh', b'01', '2000-11-28', 'HCM', '876456789', 'thungan@gmail.com', NULL, 'đại học bách khoa', 'Thu ngân', '2025-11-29', '123456789112'),
(74, 'khánh minh', b'01', '2000-11-28', 'HCM', '113456789', 'duocsi@gmail.com', NULL, 'đại học bách khoa', 'Dược sĩ', '2025-11-29', '223456789123'),
(75, 'minh tân', b'01', '2000-11-28', 'HCM', '123312321', 'kho@gmail.com', NULL, 'đại học bách khoa', 'Nhân viên kho', '2025-11-29', '987654321123'),
(77, 'hồng hà', b'01', '2000-11-28', 'HCM', '1234567891', 'trolynhikhoa@gmail.com', 'Nhi khoa', 'đại học bách khoa', 'Trợ lý bác sĩ chuyên khoa', '2025-11-29', '523456789123'),
(78, 'hồng hà', b'01', '2000-11-28', 'HCM', '987654321', 'trolytaimuihong@gmail.com', 'Tai - Mũi - Họng', 'đại học bách khoa', 'Trợ lý bác sĩ chuyên khoa', '2025-11-29', '113456789123'),
(79, 'hồng hà', b'01', '2000-11-28', 'HCM', '989898998', 'trolyranghammat@gmail.com', 'Răng - Hàm - Mặt', 'đại học bách khoa', 'Trợ lý bác sĩ chuyên khoa', '2025-11-29', '123456789345'),
(80, 'hồng hà', b'01', '2000-11-28', 'HCM', '939768935', 'trolyxetnghiem@gmail.com', 'Xét nghiệm', 'đại học bách khoa', 'Trợ lý bác sĩ chuyên khoa', '2025-11-29', '123456789876'),
(81, 'hồng hà', b'01', '2000-11-28', 'HCM', '939768936', 'trolytimmach@gmail.com', 'Tim mạch', 'đại học bách khoa', 'Trợ lý bác sĩ chuyên khoa', '2025-11-29', '123456789000'),
(87, 'minh thúy', b'00', '2000-11-28', 'HCM', '123456733', 'trolytq@gmail.com', 'Nội tổng quát', 'đại học bách khoa', 'Trợ lý bác sĩ tổng quát', '2025-11-29', '123456789888'),
(98, 'admin', b'01', '2000-12-14', 'HCM', '0123456943', 'tamduonge7@gmail.com', '', 'ĐẠI HỌC SÀI GÒN', 'Quản trị viên', '2025-12-03', '123456789011'),
(108, 'thu hà', b'01', '2000-12-14', 'HCM', '123450987', 'ktv@gmail.com', 'Xét nghiệm', 'ĐẠI HỌC SÀI GÒN', 'Kỹ thuật viên Xét nghiệm', '2025-12-03', '123456788765'),
(120, 'thái hồ', b'01', '2000-12-14', 'HCM', '123451234', 'cdha@gmail.com', 'Chẩn đoán hình ảnh', 'ĐẠI HỌC SÀI GÒN', 'Bác sĩ Chẩn đoán hình ảnh', '2025-12-03', '123456781234'),
(121, 'manh vũ', b'01', '2000-12-14', 'HCM', '0123450016', 'ktv1@gmail.com', 'Xét nghiệm', 'ĐẠI HỌC SÀI GÒN', 'Kỹ thuật viên Xét nghiệm', '2025-12-03', '123456788009'),
(122, 'manh vũ', b'01', '2000-12-14', 'HCM', '0123451298', 'letan1@gmail.com', '', 'ĐẠI HỌC SÀI GÒN', 'Lễ tân', '2025-12-03', '123456787689');

-- --------------------------------------------------------

--
-- Table structure for table `nha_cung_cap`
--

DROP TABLE IF EXISTS `nha_cung_cap`;
CREATE TABLE IF NOT EXISTS `nha_cung_cap` (
  `ma_nha_cung_cap` int NOT NULL AUTO_INCREMENT,
  `ten_nha_cung_cap` varchar(100) NOT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `so_dien_thoai` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `ghi_chu` text,
  PRIMARY KEY (`ma_nha_cung_cap`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `nha_cung_cap`
--

INSERT INTO `nha_cung_cap` (`ma_nha_cung_cap`, `ten_nha_cung_cap`, `dia_chi`, `so_dien_thoai`, `email`, `ghi_chu`) VALUES
(1, 'Công ty Dược phẩm Thành Công', '200 Lạc Long Quân, Hà Nội', '02438889999', NULL, NULL),
(3, 'cc', '', '1123213211', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `nhom_dich_vu_cls`
--

DROP TABLE IF EXISTS `nhom_dich_vu_cls`;
CREATE TABLE IF NOT EXISTS `nhom_dich_vu_cls` (
  `ma_nhom` varchar(50) NOT NULL,
  `ten_nhom_hien_thi` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ma_nhom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `nhom_dich_vu_cls`
--

INSERT INTO `nhom_dich_vu_cls` (`ma_nhom`, `ten_nhom_hien_thi`) VALUES
('HUYET_HOC', 'Tổng phân tích tế bào máu'),
('KHAC', 'Các xét nghiệm khác'),
('MIEN_DICH', 'Miễn dịch & Vi sinh'),
('NUOC_TIEU', 'Tổng phân tích nước tiểu'),
('SINH_HOA', 'Sinh hóa máu (Gan, Thận, Mỡ...)');

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
  `chan_doan_so_bo` text,
  `tong_tien` decimal(15,2) DEFAULT '0.00',
  `trang_thai` varchar(20) DEFAULT 'CHUA_THANH_TOAN',
  PRIMARY KEY (`ma_phieu_chi_dinh`),
  KEY `fk-manvv` (`ma_nhan_vien_chi_dinh`),
  KEY `fk-maphieukham` (`ma_phieu_kham`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `phieu_chi_dinh`
--

INSERT INTO `phieu_chi_dinh` (`ma_phieu_chi_dinh`, `ma_phieu_kham`, `ma_nhan_vien_chi_dinh`, `ngay_chi_dinh`, `chan_doan_so_bo`, `tong_tien`, `trang_thai`) VALUES
(14, 235, 65, '2025-12-06 21:58:32', NULL, 150000.00, 'CHUA_THANH_TOAN'),
(15, 238, 65, '2025-12-06 22:11:22', NULL, 300000.00, 'CHUA_THANH_TOAN'),
(18, 303, 65, '2025-12-08 21:50:03', NULL, 300000.00, 'CHUA_THANH_TOAN'),
(19, 307, 65, '2025-12-09 00:50:32', NULL, 300000.00, 'CHUA_THANH_TOAN'),
(20, 308, 65, '2025-12-10 09:25:49', NULL, 110000.00, 'CHUA_THANH_TOAN'),
(21, 311, 65, '2025-12-09 10:40:01', NULL, 150000.00, 'CHUA_THANH_TOAN'),
(22, 316, 65, '2025-12-09 11:23:06', NULL, 100000.00, 'CHUA_THANH_TOAN'),
(23, 323, 65, '2025-12-09 19:43:13', NULL, 300000.00, 'CHUA_THANH_TOAN'),
(25, 326, 65, '2025-12-10 20:11:20', NULL, 120000.00, 'CHUA_THANH_TOAN'),
(26, 327, 65, '2025-12-11 20:28:19', NULL, 80000.00, 'CHUA_THANH_TOAN'),
(27, 330, 65, '2025-12-10 15:42:34', NULL, 50000.00, 'CHUA_THANH_TOAN'),
(28, 334, 65, '2025-12-11 20:16:55', NULL, 120000.00, 'CHUA_THANH_TOAN'),
(29, 340, 65, '2025-12-12 20:41:05', NULL, 150000.00, 'CHUA_THANH_TOAN'),
(30, 341, 65, '2025-12-12 22:12:21', NULL, 80000.00, 'CHUA_THANH_TOAN'),
(31, 342, 65, '2025-12-13 18:53:21', NULL, 100000.00, 'CHUA_THANH_TOAN'),
(32, 343, 65, '2025-12-13 19:12:48', NULL, 50000.00, 'CHUA_THANH_TOAN'),
(33, 344, 65, '2025-12-13 23:02:29', NULL, 150000.00, 'CHUA_THANH_TOAN'),
(34, 345, 65, '2025-12-14 22:35:22', NULL, 80000.00, 'CHUA_THANH_TOAN'),
(35, 346, 65, '2025-12-15 00:54:51', NULL, 150000.00, 'CHUA_THANH_TOAN'),
(36, 350, 67, '2025-12-20 16:56:48', NULL, 150000.00, 'CHUA_THANH_TOAN');

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
) ENGINE=InnoDB AUTO_INCREMENT=351 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `phieu_kham`
--

INSERT INTO `phieu_kham` (`ma_phieu_kham`, `ma_benh_nhan`, `ma_nhan_vien`, `ma_chuyen_khoa`, `ngay_kham`, `trang_thai`, `ghi_chu`, `ngay_tao`) VALUES
(45, 11, 65, 1, '2025-12-04 13:21:37', 'Đang khám', NULL, '2025-12-04 13:21:37'),
(46, 11, 65, 1, '2025-12-04 13:23:09', 'Đang khám', NULL, '2025-12-04 13:23:09'),
(47, 11, 65, 1, '2025-12-04 13:25:07', 'Đang khám', NULL, '2025-12-04 13:25:07'),
(48, 11, 65, 1, '2025-12-04 13:26:49', 'Đang khám', NULL, '2025-12-04 13:26:49'),
(49, 11, 65, 1, '2025-12-04 13:28:59', 'Đang khám', NULL, '2025-12-04 13:28:59'),
(50, 11, 65, 1, '2025-12-04 13:31:15', 'Đang khám', NULL, '2025-12-04 13:31:15'),
(51, 11, 65, 1, '2025-12-04 13:49:05', 'Đang khám', NULL, '2025-12-04 13:49:05'),
(52, 11, 65, 1, '2025-12-04 13:55:04', 'Đang khám', NULL, '2025-12-04 13:55:04'),
(53, 11, 65, 1, '2025-12-04 13:57:43', 'Đang khám', NULL, '2025-12-04 13:57:43'),
(54, 11, 65, 1, '2025-12-04 14:00:48', 'Đang khám', NULL, '2025-12-04 14:00:48'),
(55, 11, 65, 1, '2025-12-04 14:03:55', 'Đang khám', NULL, '2025-12-04 14:03:55'),
(56, 11, 65, 1, '2025-12-04 14:06:11', 'Đang khám', NULL, '2025-12-04 14:06:11'),
(57, 11, 65, 1, '2025-12-04 14:07:58', 'Đang khám', NULL, '2025-12-04 14:07:58'),
(58, 11, 65, 1, '2025-12-04 14:09:45', 'Đang khám', NULL, '2025-12-04 14:09:45'),
(59, 11, 65, 1, '2025-12-04 14:13:12', 'Đang khám', NULL, '2025-12-04 14:13:12'),
(60, 11, 65, 1, '2025-12-04 18:28:22', 'Đang khám', NULL, '2025-12-04 18:28:22'),
(61, 11, 65, 1, '2025-12-04 18:34:51', 'Đang khám', NULL, '2025-12-04 18:34:51'),
(62, 11, 65, 1, '2025-12-04 18:37:27', 'Đang khám', NULL, '2025-12-04 18:37:27'),
(63, 11, 65, 1, '2025-12-04 18:57:07', 'Đang khám', NULL, '2025-12-04 18:57:07'),
(64, 11, 65, 1, '2025-12-04 18:58:27', 'Đang khám', NULL, '2025-12-04 18:58:27'),
(65, 11, 65, 1, '2025-12-04 19:02:47', 'Đang khám', NULL, '2025-12-04 19:02:47'),
(66, 11, 65, 1, '2025-12-04 19:05:46', 'Đang khám', NULL, '2025-12-04 19:05:46'),
(67, 11, 65, 1, '2025-12-04 19:10:08', 'Đang khám', NULL, '2025-12-04 19:10:08'),
(68, 11, 65, 1, '2025-12-04 19:17:13', 'Đang khám', NULL, '2025-12-04 19:17:13'),
(69, 11, 65, 1, '2025-12-04 19:20:07', 'Đang khám', NULL, '2025-12-04 19:20:07'),
(70, 11, 65, 1, '2025-12-04 19:21:29', 'Đang khám', NULL, '2025-12-04 19:21:29'),
(71, 11, 65, 1, '2025-12-04 19:22:01', 'Đang khám', NULL, '2025-12-04 19:22:01'),
(72, 11, 65, 1, '2025-12-04 19:22:06', 'Đang khám', NULL, '2025-12-04 19:22:06'),
(73, 11, 65, 1, '2025-12-04 19:22:07', 'Đang khám', NULL, '2025-12-04 19:22:07'),
(74, 11, 65, 1, '2025-12-04 19:25:29', 'Đang khám', NULL, '2025-12-04 19:25:29'),
(75, 11, 65, 1, '2025-12-04 19:30:26', 'Đang khám', NULL, '2025-12-04 19:30:26'),
(76, 11, 65, 1, '2025-12-04 19:31:25', 'Đang khám', NULL, '2025-12-04 19:31:25'),
(77, 11, 65, 1, '2025-12-04 19:32:40', 'Đang khám', NULL, '2025-12-04 19:32:40'),
(81, 11, 65, 1, '2025-12-04 22:11:58', 'Đang khám', NULL, '2025-12-04 22:11:58'),
(82, 11, 65, 1, '2025-12-04 22:15:12', 'Đang khám', NULL, '2025-12-04 22:15:12'),
(83, 11, 65, 1, '2025-12-04 22:17:25', 'Đang khám', NULL, '2025-12-04 22:17:25'),
(84, 11, 65, 1, '2025-12-04 22:18:54', 'Đang khám', NULL, '2025-12-04 22:18:54'),
(85, 11, 65, 1, '2025-12-04 22:30:51', 'Đang khám', NULL, '2025-12-04 22:30:51'),
(86, 11, 87, 1, '2025-12-05 09:22:12', 'CHO_KHAM_BS', NULL, '2025-12-05 09:22:12'),
(87, 11, 65, 1, '2025-12-05 09:22:42', 'Đang khám', NULL, '2025-12-05 09:22:42'),
(88, 11, 65, 1, '2025-12-05 09:24:22', 'Đang khám', NULL, '2025-12-05 09:24:22'),
(89, 11, 65, 1, '2025-12-05 09:27:54', 'Đang khám', NULL, '2025-12-05 09:27:54'),
(90, 11, 65, 1, '2025-12-05 16:52:58', 'Đang khám', NULL, '2025-12-05 16:52:58'),
(121, 6, 87, 1, '2025-12-05 21:51:19', 'CHO_KHAM_BS', NULL, '2025-12-05 21:51:19'),
(122, 6, 65, 1, '2025-12-05 21:51:46', 'Đang khám', NULL, '2025-12-05 21:51:46'),
(128, 6, 65, 1, '2025-12-05 22:14:30', 'Đang khám', NULL, '2025-12-05 22:14:30'),
(129, 6, 65, 1, '2025-12-05 22:15:14', 'Đang khám', NULL, '2025-12-05 22:15:14'),
(155, 6, 87, 1, '2025-12-06 00:01:52', 'CHO_KHAM_BS', NULL, '2025-12-06 00:01:52'),
(156, 6, 65, 1, '2025-12-06 00:02:14', 'Đang khám', NULL, '2025-12-06 00:02:14'),
(164, 1, 87, 1, '2025-12-06 00:25:19', 'CHO_KHAM_BS', NULL, '2025-12-06 00:25:19'),
(165, 1, 65, 1, '2025-12-06 00:25:37', 'Đang khám', NULL, '2025-12-06 00:25:37'),
(229, 1, 65, 1, '2025-12-06 21:32:31', 'Đang khám', NULL, '2025-12-06 21:32:31'),
(230, 6, 65, 1, '2025-12-06 21:32:37', 'Đang khám', NULL, '2025-12-06 21:32:37'),
(231, 6, 65, 1, '2025-12-06 21:33:35', 'Đang khám', NULL, '2025-12-06 21:33:35'),
(232, 6, 65, 1, '2025-12-06 21:35:17', 'Đang khám', NULL, '2025-12-06 21:35:17'),
(233, 6, 65, 1, '2025-12-06 21:38:44', 'Đang khám', NULL, '2025-12-06 21:38:44'),
(234, 1, 65, 1, '2025-12-06 21:47:49', 'Đang khám', NULL, '2025-12-06 21:47:49'),
(235, 11, 87, 1, '2025-12-06 21:58:12', 'CHO_KHAM_BS', NULL, '2025-12-06 21:58:12'),
(236, 11, 65, 1, '2025-12-06 21:58:29', 'Đang khám', NULL, '2025-12-06 21:58:29'),
(237, 11, 65, 1, '2025-12-06 22:00:07', 'Đang khám', NULL, '2025-12-06 22:00:07'),
(238, 1, 87, 1, '2025-12-06 22:10:44', 'CHO_KHAM_BS', NULL, '2025-12-06 22:10:44'),
(239, 1, 65, 1, '2025-12-06 22:11:17', 'Đang khám', NULL, '2025-12-06 22:11:17'),
(247, 11, 87, 1, '2025-12-07 00:01:08', 'CHO_KHAM_BS', NULL, '2025-12-07 00:01:08'),
(248, 11, 65, 1, '2025-12-07 00:01:28', 'Đang khám', NULL, '2025-12-07 00:01:28'),
(257, 11, 65, 1, '2025-12-07 01:23:43', 'Đang khám', NULL, '2025-12-07 01:23:43'),
(258, 11, 65, 1, '2025-12-07 01:26:05', 'Đang khám', NULL, '2025-12-07 01:26:05'),
(259, 11, 65, 1, '2025-12-07 01:27:50', 'Đang khám', NULL, '2025-12-07 01:27:50'),
(260, 11, 65, 1, '2025-12-07 01:30:08', 'Đang khám', NULL, '2025-12-07 01:30:08'),
(261, 11, 65, 1, '2025-12-07 01:32:47', 'Đang khám', NULL, '2025-12-07 01:32:47'),
(262, 11, 65, 1, '2025-12-07 01:35:44', 'Đang khám', NULL, '2025-12-07 01:35:44'),
(264, 11, 65, 1, '2025-12-07 01:49:57', 'Đang khám', NULL, '2025-12-07 01:49:57'),
(265, 9, 87, 1, '2025-12-07 02:10:02', 'Đang khám', NULL, '2025-12-07 02:10:02'),
(266, 6, 87, 1, '2025-12-07 02:13:38', 'Đang khám', NULL, '2025-12-07 02:13:38'),
(267, 3, 87, 1, '2025-12-07 02:15:17', 'Đang khám', NULL, '2025-12-07 02:15:17'),
(268, 1, 87, 1, '2025-12-07 02:18:50', 'CHO_KHAM_BS', NULL, '2025-12-07 02:18:50'),
(269, 1, 65, 1, '2025-12-07 02:19:30', 'Đang khám', NULL, '2025-12-07 02:19:30'),
(270, 1, 87, 1, '2025-12-07 02:22:08', 'Đang khám', NULL, '2025-12-07 02:22:08'),
(271, 11, 65, 1, '2025-12-07 22:28:31', 'Đang khám', NULL, '2025-12-07 22:28:31'),
(272, 11, 65, 1, '2025-12-07 22:30:33', 'Đang khám', NULL, '2025-12-07 22:30:33'),
(273, 11, 87, 1, '2025-12-07 22:52:36', 'Đang khám', NULL, '2025-12-07 22:52:36'),
(274, 11, 87, 1, '2025-12-07 22:52:40', 'Đang khám', NULL, '2025-12-07 22:52:40'),
(275, 11, 87, 1, '2025-12-07 23:01:02', 'CHO_KHAM_BS', NULL, '2025-12-07 23:01:02'),
(276, 11, 87, 1, '2025-12-07 23:01:16', 'Đang khám', NULL, '2025-12-07 23:01:16'),
(277, 11, 87, 1, '2025-12-07 23:03:12', 'Đang khám', NULL, '2025-12-07 23:03:12'),
(278, 11, 87, 1, '2025-12-07 23:03:18', 'Đang khám', NULL, '2025-12-07 23:03:18'),
(279, 9, 87, 1, '2025-12-07 23:03:26', 'Đang khám', NULL, '2025-12-07 23:03:26'),
(280, 11, 87, 1, '2025-12-07 23:05:15', 'Đang khám', NULL, '2025-12-07 23:05:15'),
(281, 1, 87, 1, '2025-12-07 23:05:33', 'Đang khám', NULL, '2025-12-07 23:05:33'),
(282, 11, 87, 1, '2025-12-07 23:09:07', 'Đang khám', NULL, '2025-12-07 23:09:07'),
(283, 9, 87, 1, '2025-12-07 23:09:19', 'CHO_KHAM_BS', NULL, '2025-12-07 23:09:19'),
(284, 11, 87, 1, '2025-12-07 23:11:40', 'CHO_KHAM_BS', NULL, '2025-12-07 23:11:40'),
(285, 11, 87, 1, '2025-12-07 23:16:19', 'Đang khám', NULL, '2025-12-07 23:16:19'),
(286, 11, 87, 1, '2025-12-07 23:17:15', 'CHO_KHAM_BS', NULL, '2025-12-07 23:17:15'),
(287, 11, 87, 1, '2025-12-07 23:17:26', 'Đang khám', NULL, '2025-12-07 23:17:26'),
(303, 6, 87, 1, '2025-12-08 21:46:00', 'BS_DANG_KHAM', NULL, '2025-12-08 21:46:00'),
(306, 11, 87, 5, '2025-12-09 00:15:44', 'BS_DANG_KHAM_BENH', 'cc', '2025-12-09 00:15:44'),
(307, 3, 87, 1, '2025-12-09 00:50:03', 'DA_KHAM', NULL, '2025-12-09 00:50:03'),
(308, 3, 87, 1, '2025-12-10 09:25:23', 'DA_KHAM', NULL, '2025-12-10 09:25:23'),
(309, 3, 87, 1, '2025-12-10 09:26:47', 'BS_DANG_KHAM_BENH', NULL, '2025-12-10 09:26:47'),
(310, 13, 87, 1, '2025-12-09 10:35:58', 'CHO_KHAM_BS', NULL, '2025-12-09 10:35:58'),
(311, 13, 87, 1, '2025-12-09 10:38:22', 'DA_KHAM', NULL, '2025-12-09 10:38:22'),
(312, 10, 87, 1, '2025-12-09 10:53:55', 'Đang khám', NULL, '2025-12-09 10:53:55'),
(313, 10, 87, 1, '2025-12-09 10:54:44', 'Đang khám', NULL, '2025-12-09 10:54:44'),
(314, 10, 87, 1, '2025-12-09 10:54:47', 'Đang khám', NULL, '2025-12-09 10:54:47'),
(315, 10, 87, 1, '2025-12-09 10:54:50', 'CHO_KHAM_BS', NULL, '2025-12-09 10:54:50'),
(316, 10, 87, 1, '2025-12-09 10:55:02', 'BS_DANG_KHAM_BENH', NULL, '2025-12-09 10:55:02'),
(317, 6, 87, 1, '2025-12-09 19:26:28', 'CHO_KHAM_BS', NULL, '2025-12-09 19:26:28'),
(318, 6, 87, 1, '2025-12-09 19:27:30', 'Đang khám', NULL, '2025-12-09 19:27:30'),
(319, 6, 87, 1, '2025-12-09 19:30:22', 'Đang khám', NULL, '2025-12-09 19:30:22'),
(320, 6, 87, 1, '2025-12-09 19:35:45', 'Đang khám', NULL, '2025-12-09 19:35:45'),
(321, 6, 87, 1, '2025-12-09 19:35:59', 'Đang khám', NULL, '2025-12-09 19:35:59'),
(322, 6, 87, 1, '2025-12-09 19:37:58', 'CHO_KHAM_BS', NULL, '2025-12-09 19:37:58'),
(323, 2, 87, 1, '2025-12-09 19:41:45', 'BS_DANG_KHAM_BENH', NULL, '2025-12-09 19:41:45'),
(326, 2, 87, 1, '2025-12-10 20:10:41', 'DA_KHAM', NULL, '2025-12-10 20:10:41'),
(327, 2, 87, 1, '2025-12-11 20:12:59', 'BS_DANG_KHAM_BENH', NULL, '2025-12-11 20:12:59'),
(328, 1, 87, 3, '2025-12-10 14:14:28', 'BS_DANG_KHAM_BENH', '', '2025-12-10 14:14:28'),
(329, 1, 77, 3, '2025-12-10 14:47:40', 'DA_KHAM', NULL, '2025-12-10 14:47:40'),
(330, 12, 87, 1, '2025-12-10 15:40:36', 'BS_DANG_KHAM_BENH', NULL, '2025-12-10 15:40:36'),
(331, 6, 77, 3, '2025-12-10 21:18:17', 'BS_DANG_KHAM_BENH', NULL, '2025-12-10 21:18:17'),
(332, 1, 77, 3, '2025-12-11 21:23:33', 'BS_DANG_KHAM_BENH', NULL, '2025-12-11 21:23:33'),
(333, 6, 79, 5, '2025-12-11 22:34:35', 'BS_DANG_KHAM_BENH', NULL, '2025-12-11 22:34:35'),
(334, 11, 87, 1, '2025-12-11 23:04:52', 'BS_DANG_KHAM_BENH', NULL, '2025-12-11 23:04:52'),
(335, 2, 87, 1, '2025-12-12 11:01:46', 'BS_DANG_KHAM_BENH', NULL, '2025-12-12 11:01:46'),
(336, 14, 78, 4, '2025-12-11 22:33:03', 'BS_DANG_KHAM_BENH', NULL, '2025-12-11 22:33:03'),
(337, 15, 81, 11, '2025-12-11 23:18:53', 'CHO_KHAM_BS', NULL, '2025-12-11 23:18:53'),
(338, 6, 87, 1, '2025-12-12 14:30:44', 'DA_THANH_TOAN', NULL, '2025-12-12 14:30:44'),
(339, 14, 87, 1, '2025-12-12 15:40:50', 'DA_THANH_TOAN', NULL, '2025-12-12 15:40:50'),
(340, 12, 87, 1, '2025-12-12 20:39:45', 'DA_THANH_TOAN', NULL, '2025-12-12 20:39:45'),
(341, 3, 87, 1, '2025-12-12 22:11:54', 'DA_THANH_TOAN', NULL, '2025-12-12 22:11:54'),
(342, 15, 87, 1, '2025-12-13 18:52:28', 'DA_CAP_THUOC', NULL, '2025-12-13 18:52:28'),
(343, 6, 87, 1, '2025-12-13 19:12:02', 'DA_CAP_THUOC', NULL, '2025-12-13 19:12:02'),
(344, 10, 87, 1, '2025-12-13 23:01:55', 'DA_KHAM', NULL, '2025-12-13 23:01:55'),
(345, 15, 87, 1, '2025-12-14 14:16:11', 'DA_THANH_TOAN', NULL, '2025-12-14 14:16:11'),
(346, 1, 87, 1, '2025-12-15 00:54:20', 'DA_THANH_TOAN', NULL, '2025-12-15 00:54:20'),
(347, 16, 87, 1, '2025-12-15 01:21:14', 'BS_DANG_KHAM_BENH', NULL, '2025-12-15 01:21:14'),
(348, 16, 87, 1, '2025-12-16 00:17:14', 'DA_CAP_THUOC', NULL, '2025-12-16 00:17:14'),
(349, 11, 87, 1, '2025-12-19 12:39:30', 'BS_DANG_KHAM_BENH', NULL, '2025-12-19 12:39:30'),
(350, 16, 87, 3, '2025-12-20 16:34:32', 'DA_THANH_TOAN', '', '2025-12-20 16:34:32');

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `phieu_nhap_thuoc`
--

INSERT INTO `phieu_nhap_thuoc` (`ma_phieu_nhap_thuoc`, `ngay_nhap`, `ma_nhan_vien_nhap`, `ma_nha_cung_cap`, `tong_tien_nhap`, `trang_thai`, `ghi_chu`) VALUES
(6, '2025-11-20 20:13:51', 75, 1, 50000.00, 'Hoàn thành', NULL),
(7, '2025-11-20 20:21:38', 75, 1, 0.00, 'Đang tạo', NULL),
(8, '2025-11-20 20:22:28', 75, 1, 25000.00, 'Hoàn thành', NULL),
(9, '2025-11-20 20:39:30', 75, 1, 5000.00, 'Hoàn thành', NULL),
(10, '2025-11-20 21:01:02', 75, 1, 0.00, 'Đang tạo', NULL),
(11, '2025-11-20 21:08:01', 75, 1, 5000.00, 'Hoàn thành', NULL),
(12, '2025-11-20 23:36:44', 75, 1, 500000.00, 'Hoàn thành', NULL),
(13, '2025-11-24 22:12:10', 75, 3, 0.00, 'Đang tạo', NULL),
(14, '2025-11-24 22:13:23', 75, 1, 50000.00, 'Hoàn thành', NULL),
(15, '2025-11-24 22:17:27', 75, 3, 2000000.00, 'Hoàn thành', NULL),
(16, '2025-11-24 22:19:23', 75, 3, 0.00, 'Đang tạo', NULL),
(17, '2025-11-24 22:44:04', 75, 3, 0.00, 'Đang tạo', NULL),
(18, '2025-11-24 22:45:23', 75, 1, 0.00, 'Đang tạo', NULL),
(19, '2025-11-24 22:50:52', 75, 1, 0.00, 'Đang tạo', NULL),
(20, '2025-11-24 23:07:17', 75, 1, 0.00, 'Đang tạo', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `phong_chuc_nang`
--

DROP TABLE IF EXISTS `phong_chuc_nang`;
CREATE TABLE IF NOT EXISTS `phong_chuc_nang` (
  `ma_phong` int NOT NULL AUTO_INCREMENT,
  `ten_phong` varchar(50) NOT NULL,
  `loai_phong` varchar(50) NOT NULL,
  PRIMARY KEY (`ma_phong`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `phong_chuc_nang`
--

INSERT INTO `phong_chuc_nang` (`ma_phong`, `ten_phong`, `loai_phong`) VALUES
(1, 'P. Khám Nội 1', 'KHOA_NOI'),
(2, 'P. Siêu âm 01', 'CLS_CHAN_DOAN_HINH_ANH'),
(3, 'P. X-Quang 01', 'CLS_CHAN_DOAN_HINH_ANH'),
(4, 'P. Xét nghiệm', 'CLS_XET_NGHIEM'),
(5, 'P. Khám Nhi', 'KHAM_CHUYEN_KHOA'),
(6, 'P. Tai Mũi Họng', 'KHAM_CHUYEN_KHOA'),
(7, 'P. Răng Hàm Mặt', 'KHAM_CHUYEN_KHOA'),
(8, 'P. Tim mạch', 'KHAM_CHUYEN_KHOA'),
(11, 'cc', 'CLS_CHAN_DOAN_HINH_ANH');

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
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tai_khoan`
--

INSERT INTO `tai_khoan` (`ma_tai_khoan`, `username`, `email`, `mat_khau`, `ma_nhan_vien`, `vai_tro`, `lan_dau_dang_nhap`) VALUES
(61, 'thanh trúc', 'taimuihong@gmail.com', '$2a$05$xDtjmOcE8fMWpjVuukM6numPqGEheY.6aEX8PpcxoDezv3BqxaHqy', 68, 'BAC_SI_CHUYEN_KHOA', b'0'),
(62, 'thanh trúc', 'ranghammat@gmail.com', '$2a$05$WhQTEZKeq7NdK2nS4XN7V.XeWoI7YdMm1sRtETSsj/RSYBIKyE3oK', 69, 'BAC_SI_CHUYEN_KHOA', b'0'),
(63, 'thanh trúc', 'xetnghiem@gmail.com', '$2a$05$xE65iI2dvBSJEeEtOws6b.EeNFBi6Zr9LlfOF12o8jCIpw7HCSnoy', 70, 'BAC_SI_CHUYEN_KHOA', b'1'),
(64, 'thanh trúc', 'timmach@gmail.com', '$2a$05$50t6x1cFnZApuiTSlc7ieeZ.gLCVD5h1dCCTIYhIaMAkS8HIyFewW', 71, 'BAC_SI_CHUYEN_KHOA', b'0'),
(65, 'thanh hà', 'letan@gmail.com', '$2a$05$yrUrOpGdRPV0v6r1GiNnJOyK6mt8JNGA/tzjhaw7hUa6/BOON4Hre', 72, 'LE_TAN', b'0'),
(66, 'thành minh', 'thungan@gmail.com', '$2a$05$2lz/vm0GxbjZFta1vHBaVe/6c3sLHMbQ8BK/bnUdIhmAtLvaUhpbO', 73, 'THU_NGAN', b'0'),
(67, 'khánh minh', 'duocsi@gmail.com', '$2a$05$furhYWKDqJfHyY5ZzxNKEOa7JNXZPTBa2rHsWeuGCKOmzftF6CaqC', 74, 'DUOC_SI', b'0'),
(68, 'minh tân', 'kho@gmail.com', '$2a$05$R5E8ns23h.ShpBHKID2dAOLGGSkwqXRKyf6svkwFWQrtSmarmGZgG', 75, 'NHAN_VIEN_KHO', b'0'),
(70, 'hồng hà', 'trolynhikhoa@gmail.com', '$2a$05$ZRIHA.NujJVmPxs/ojNu9uxD2JMMUkppA1jJyO5jlVXzIYv6nR69u', 77, 'TRO_LY_BAC_SI_CHUYEN_KHOA', b'0'),
(71, 'hồng hà', 'trolytaimuihong@gmail.com', '$2a$05$mogv2xXKtGELKAPVRQ37Veje6lzBbi4owDvOXoGITnvZ79pVZ4Qny', 78, 'TRO_LY_BAC_SI_CHUYEN_KHOA', b'0'),
(72, 'hồng hà', 'trolyranghammat@gmail.com', '$2a$05$26uF6/qKwq6oNvddPY51fOgfSvmc4k586LQbaynG5CYoFv58PLz8K', 79, 'TRO_LY_BAC_SI_CHUYEN_KHOA', b'0'),
(73, 'hồng hà', 'trolyxetnghiem@gmail.com', '$2a$05$s/IilDg/nir9t9D3urUCMO6ZUo75DQaXXS7Zn6FeIxif/IDAWTPKu', 80, 'TRO_LY_BAC_SI_CHUYEN_KHOA', b'1'),
(74, 'hồng hà', 'trolytimmach@gmail.com', '$2a$05$skyDWA4XJ/0V/zQHjCIVMuFfQNmMwgno0dxV7BujlNEwLGeJL.lp.', 81, 'TRO_LY_BAC_SI_CHUYEN_KHOA', b'0'),
(75, 'minh thúy', 'trolytq@gmail.com', '$2a$05$iCjwXZ4/fwKYGKWZy3sZ5O.5/OaZbTXbscFbz7PGWoNLDyCMwKbMG', 87, 'TRO_LY_BAC_SI_TONG_QUAT', b'0'),
(87, 'thanhtrúc', 'bstq@gmail.com', '$2a$05$3Fa/n1bXynIKTbogPjLl8uD2MwsT9RQKaqEvJnUWfvkTLu32u6aoG', 65, 'BAC_SI_TONG_QUAT', b'0'),
(88, 'thanhtrúc', 'nhikhoa@gmail.com', '$2a$05$eiNW8wSnzM4uV6A4bIVmGeALGD5fVNo2iH1G3LkkLV2lwbX6Qb0Le', 67, 'BAC_SI_CHUYEN_KHOA', b'0'),
(89, 'admin', 'tamduonge7@gmail.com', '$2a$05$4k7PbGcMsTZXwXdW1o9tSOBnCoCS2x..IqTvwWBt3uVYBOtBPFFL.', 98, 'QUAN_TRI_VIEN', b'0'),
(96, 'thu hà', 'ktv@gmail.com', '$2a$05$BIpRycXXarPbvFntEqHIRuhCKOaEYG4xkWRsDwl/8eCeJ41r.1DJ6', 108, 'KY_THUAT_VIEN_XET_NGHIEM', b'0'),
(105, 'thu hà', 'cdha@gmail.com', '$2a$05$oko8F6eIvjgEymkcyx.gL.FatsG0EzeqM5D0zHFkP5dUs6IV2F/mq', 120, 'BAC_SI_CHAN_DOAN_HINH_ANH', b'0'),
(107, 'manh vũ', 'letan1@gmail.com', '$2a$05$Pbs4QzvSPhwUL5ITTLcB7exId85Jtrppz3mOduVBcZXVlCVyeC06a', 122, 'LE_TAN', b'0');

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
  PRIMARY KEY (`ma_thuoc`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `thuoc`
--

INSERT INTO `thuoc` (`ma_thuoc`, `ten_thuoc`, `hoat_chat`, `ham_luong`, `dang_thuoc`, `loai_thuoc`, `don_vi_tinh`, `don_gia_nhap`, `don_gia_ban`, `ngay_san_xuat`, `han_su_dung`, `nha_san_xuat`, `nuoc_san_xuat`, `ghi_chu`) VALUES
(1, 'Paracetamol 500mg', 'Paracetamol', '500mg', 'Viên nén', 'Uống', 'viên', 0.00, 5000.00, '2025-01-10', '2027-01-10', 'DHG Pharma', 'Việt Nam', NULL),
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
(15, 'Zyrtec123', 'Cetirizine', '10mg', 'Viên nén', 'Dị ứng', 'Vỉ', 0.00, 0.00, '2024-04-01', '2027-04-01', 'GSK', 'Thụy Sĩ', 'Viêm mũi'),
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
  `tong_tien` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  PRIMARY KEY (`ma_toa_thuoc`),
  KEY `ma_phieu_kham` (`ma_phieu_kham`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `toa_thuoc`
--

INSERT INTO `toa_thuoc` (`ma_toa_thuoc`, `ma_phieu_kham`, `ghi_chu`, `ngay_tao`, `tong_tien`) VALUES
(4, 121, '', '2025-12-05 23:55:48', ''),
(5, 155, '', '2025-12-06 00:02:19', ''),
(6, 164, '', '2025-12-06 00:25:42', ''),
(7, 247, '', '2025-12-07 01:50:01', ''),
(9, 307, '', '2025-12-09 09:23:59', ''),
(10, 308, '', '2025-12-10 09:26:11', ''),
(11, 311, '', '2025-12-09 10:44:26', ''),
(12, 323, '', '2025-12-09 19:43:42', ''),
(14, 326, '', '2025-12-10 20:11:30', ''),
(15, 327, '', '2025-12-11 20:47:16', ''),
(16, 329, '', '2025-12-10 21:07:50', ''),
(18, 335, '', '2025-12-12 11:20:04', ''),
(19, 339, '', '2025-12-12 15:48:28', '180,000'),
(20, 340, '', '2025-12-12 20:41:11', '500,000'),
(21, 341, '', '2025-12-12 22:12:29', '75,000'),
(22, 342, '', '2025-12-13 18:53:26', '30,000'),
(23, 343, '', '2025-12-13 19:12:53', '120,000'),
(24, 344, '', '2025-12-13 23:02:34', '850,000'),
(25, 345, '', '2025-12-14 22:06:33', '224,000'),
(26, 346, '', '2025-12-15 00:54:57', '280,040'),
(27, 347, '', '2025-12-15 23:35:30', '0'),
(28, 348, '', '2025-12-16 00:17:37', '1,840,000'),
(29, 350, '', '2025-12-20 16:56:53', '40,000');

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
('BAC_SI_CHAN_DOAN_HINH_ANH', 'Bác sĩ Chẩn đoán hình ảnh'),
('BAC_SI_CHUYEN_KHOA', 'Bác sĩ chuyên khoa'),
('BAC_SI_TONG_QUAT', 'Bác sĩ tổng quát'),
('DUOC_SI', 'Dược sĩ'),
('KY_THUAT_VIEN_XET_NGHIEM', 'Kỹ thuật viên Xét nghiệm'),
('LE_TAN', 'Lễ tân'),
('NHAN_VIEN_KHO', 'Nhân viên kho'),
('QUAN_TRI_VIEN', 'Quản trị viên'),
('THU_NGAN', 'Thu ngân'),
('TRO_LY_BAC_SI_CHUYEN_KHOA', 'Trợ lý bác sĩ chuyên khoa'),
('TRO_LY_BAC_SI_TONG_QUAT', 'Trợ lý bác sĩ tổng quát');

-- --------------------------------------------------------

--
-- Stand-in structure for view `view_danhsachchocls`
-- (See below for the actual view)
--
DROP VIEW IF EXISTS `view_danhsachchocls`;
CREATE TABLE IF NOT EXISTS `view_danhsachchocls` (
`bac_si_chi_dinh` varchar(40)
,`gioi_tinh` bit(2)
,`loai_dich_vu` varchar(50)
,`ma_benh_nhan` int
,`ma_chi_tiet` int
,`ma_phieu_chi_dinh` int
,`nam_sinh` year
,`ngay_chi_dinh` datetime
,`ten_benh_nhan` varchar(30)
,`ten_dich_vu` varchar(50)
,`trang_thai_dv` varchar(20)
);

-- --------------------------------------------------------

--
-- Structure for view `view_danhsachchocls`
--
DROP TABLE IF EXISTS `view_danhsachchocls`;

DROP VIEW IF EXISTS `view_danhsachchocls`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_danhsachchocls`  AS SELECT `ct`.`id` AS `ma_chi_tiet`, `p`.`ma_phieu_chi_dinh` AS `ma_phieu_chi_dinh`, `bn`.`ma_benh_nhan` AS `ma_benh_nhan`, `bn`.`ho_ten` AS `ten_benh_nhan`, year(`bn`.`ngay_sinh`) AS `nam_sinh`, `bn`.`gioi_tinh` AS `gioi_tinh`, `dv`.`ten_dich_vu` AS `ten_dich_vu`, `dv`.`loai_dich_vu` AS `loai_dich_vu`, `nv`.`ho_ten` AS `bac_si_chi_dinh`, `ct`.`trang_thai_dv` AS `trang_thai_dv`, `p`.`ngay_chi_dinh` AS `ngay_chi_dinh` FROM (((((`chi_tiet_chi_dinh` `ct` join `phieu_chi_dinh` `p` on((`ct`.`ma_phieu_chi_dinh` = `p`.`ma_phieu_chi_dinh`))) join `phieu_kham` `pk` on((`p`.`ma_phieu_kham` = `pk`.`ma_phieu_kham`))) join `benh_nhan` `bn` on((`pk`.`ma_benh_nhan` = `bn`.`ma_benh_nhan`))) join `dich_vu` `dv` on((`ct`.`ma_dich_vu` = `dv`.`ma_dich_vu`))) join `nhan_vien` `nv` on((`p`.`ma_nhan_vien_chi_dinh` = `nv`.`ma_nhan_vien`))) ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bang_phan_cong_ca_lam`
--
ALTER TABLE `bang_phan_cong_ca_lam`
  ADD CONSTRAINT `fk_phancong_nhanvien` FOREIGN KEY (`ma_nhan_vien`) REFERENCES `nhan_vien` (`ma_nhan_vien`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
