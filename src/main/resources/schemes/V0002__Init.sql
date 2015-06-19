CREATE TABLE `accounts` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `repository` int(11) DEFAULT NULL,
  `name` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3239 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `branches` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `lastRevision` int(11) NOT NULL,
  `repository` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `changes` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `revision` int(11) NOT NULL,
  `type` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '',
  `path` varchar(2048) COLLATE utf8_bin NOT NULL DEFAULT '',
  `resource` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=156050 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `repository` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(512) COLLATE utf8_bin NOT NULL DEFAULT '',
  `name` varchar(512) COLLATE utf8_bin NOT NULL DEFAULT '',
  `headRevision` int(11) DEFAULT NULL,
  `workerId` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `workerPassword` varchar(60) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `resources` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `repository` int(11) DEFAULT NULL,
  `path` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24358 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `revisions` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `branch` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  `message` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34673 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



INSERT INTO `repository` (`id`, `url`, `name`, `headRevision`, `workerId`, `workerPassword`)
VALUES
	(1, X'73766E3A2F2F3132342E3234332E39312E3232302F6568696D617274', X'6568696D617274', NULL, X'6A73616E36', X'6A73616E36'),
	(2, X'73766E3A2F2F3132342E3234332E39312E3232303A383838382F64657369676E', X'64657369676E', NULL, X'6A73616E36', X'6A73616E36');

