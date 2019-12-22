SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_auth_user
-- ----------------------------
DROP TABLE IF EXISTS `c_auth_user`;
CREATE TABLE `c_auth_user`  (
`id` bigint(20) NOT NULL COMMENT 'ID',
`account` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
`name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
`org_id` bigint(20) NULL DEFAULT NULL COMMENT '组织ID\n#c_core_org',
`station_id` bigint(20) NULL DEFAULT NULL COMMENT '岗位ID\n#c_core_station',
`email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
`mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机',
`sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '性别\n#Sex{W:女;M:男;N:未知}',
`status` bit(1) NULL DEFAULT b'0' COMMENT '启用状态 1启用 0禁用',
`avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像',
`work_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '工作描述\r\n比如：  市长、管理员、局长等等   用于登陆展示',
`password_error_last_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次输错密码时间',
`password_error_num` int(11) NULL DEFAULT 0 COMMENT '密码错误次数',
`password_expire_time` datetime(0) NULL DEFAULT NULL COMMENT '密码过期时间',
`password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
`last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
`create_user` bigint(20) NULL DEFAULT 0 COMMENT '创建人id',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_user` bigint(20) NULL DEFAULT 0 COMMENT '更新人id',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE,
UNIQUE INDEX `UN_ACCOUNT`(`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
