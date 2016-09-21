-- phpMyAdmin SQL Dump
-- version 4.6.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Erstellungszeit: 21. Sep 2016 um 18:07
-- Server-Version: 5.5.50-MariaDB
-- PHP-Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `gw`
--

--
-- Daten für Tabelle `battle`
--

INSERT INTO `battle` (`id`, `fk_planet`, `status`, `initiated_at`, `started_at`, `ended_at`, `attacking_faction`, `defending_faction`, `winning_faction`) VALUES
(1, 1, 'F', '1900-01-01 00:00:00', NULL, NULL, '', '', 'S'),
(2, 2, 'F', '1900-01-01 00:00:00', NULL, NULL, '', '', 'U'),
(3, 3, 'F', '1900-01-01 00:00:00', NULL, NULL, '', '', 'A'),
(4, 4, 'F', '1900-01-01 00:00:00', NULL, NULL, '', '', 'C'),
(5, 5, 'F', '1900-01-01 00:00:00', NULL, NULL, '', '', 'A'),
(6, 6, 'F', '1900-01-01 00:00:00', NULL, NULL, '', '', 'S'),
(7, 7, 'F', '1900-01-01 00:00:00', NULL, NULL, '', '', 'C'),
(8, 8, 'F', '1900-01-01 00:00:00', NULL, NULL, '', '', 'C');

--
-- Daten für Tabelle `character`
--

INSERT INTO `character` (`id`, `faf_id`, `name`, `faction`, `killed_by`) VALUES
(1, 1, 'UEF#1', 'U', NULL),
(2, 2, 'UEF#2', 'U', 8),
(3, 3, 'Aeon#1', 'A', NULL),
(4, 4, 'Aeon#2', 'A', NULL),
(5, 5, 'Cybran#1', 'C', NULL),
(6, 6, 'Cybran#2', 'C', NULL),
(7, 7, 'Sera#1', 'S', NULL),
(8, 8, 'Sera#2', 'S', NULL);

--
-- Daten für Tabelle `credit_journal`
--

INSERT INTO `credit_journal` (`id`, `fk_character`, `fk_battle`, `fk_unit_transaction`, `reason`, `amount`, `transaction_date`) VALUES
(1, 1, NULL, NULL, 'R', 1000, '2016-07-22 23:07:35'),
(2, 2, NULL, NULL, 'R', 1000, '2016-07-22 23:07:35'),
(3, 3, NULL, NULL, 'R', 1000, '2016-07-22 23:08:09'),
(4, 4, NULL, NULL, 'R', 1000, '2016-07-22 23:08:09'),
(5, 5, NULL, NULL, 'R', 1000, '2016-07-22 23:11:44'),
(6, 6, NULL, NULL, 'R', 1000, '2016-07-22 23:11:44'),
(7, 7, NULL, NULL, 'R', 1000, '2016-07-22 23:12:53'),
(8, 8, NULL, NULL, 'R', 1000, '2016-07-22 23:12:53');

--
-- Daten für Tabelle `map_pool`
--

INSERT INTO `map_pool` (`id`, `faf_map_id`, `faf_map_version`, `total_slots`, `size`, `ground`) VALUES
(1, 1, 1, 4, 10, 'S'),
(2, 2, 1, 2, 5, 'F'),
(3, 3, 1, 6, 20, 'L'),
(4, 4, 2, 10, 40, 'W'),
(5, 5, 2, 2, 10, 'D');

--
-- Daten für Tabelle `planet`
--

INSERT INTO `planet` (`id`, `fk_sun_system`, `fk_map`, `orbit_level`, `size`, `habitable`, `ground`) VALUES
(1, 1, 5, 1, 10, 1, 'D'),
(2, 2, 1, 5, 5, 1, 'F'),
(3, 3, 4, 3, 40, 1, 'W'),
(4, 4, 1, 3, 10, 1, 'S'),
(5, 4, 2, 4, 5, 1, 'F'),
(6, 5, 3, 1, 20, 1, 'L'),
(7, 5, 2, 5, 5, 1, 'F'),
(8, 6, 1, 3, 10, 1, 'S');

--
-- Daten für Tabelle `promotion`
--

INSERT INTO `promotion` (`id`, `fk_character`, `fk_battle`, `new_rank`, `created_at`) VALUES
(3, 1, NULL, 1, '0000-00-00 00:59:20'),
(4, 2, NULL, 1, '0000-00-00 00:59:31'),
(5, 3, NULL, 1, '0000-00-00 00:59:43'),
(6, 4, NULL, 1, '0000-00-00 00:59:50'),
(7, 5, NULL, 1, '0000-00-00 01:00:02'),
(8, 6, NULL, 1, '0000-00-00 01:00:08'),
(9, 7, NULL, 1, '0000-00-00 01:00:13'),
(10, 8, NULL, 1, '0000-00-00 01:00:19'),
(11, 1, NULL, 2, '2016-09-20 00:00:00');

--
-- Daten für Tabelle `quantum_gate_link`
--

INSERT INTO `quantum_gate_link` (`fk_sun_system_from`, `fk_sun_system_to`) VALUES
(1, 2),
(2, 1),
(1, 3),
(3, 1),
(1, 4),
(4, 1),
(1, 5),
(5, 1),
(4, 6),
(6, 4),
(6, 5),
(2, 3),
(3, 2);

--
-- Daten für Tabelle `rank`
--

INSERT INTO `rank` (`level`, `xp_min`, `max_per_faction`, `uef_title`, `aeon_title`, `cybran_title`, `seraph_title`) VALUES
(1, 0, NULL, 'Recruit', 'Recruit', 'Recruit', 'Recruit'),
(2, 2000, NULL, 'Cadet', 'Initiate', 'Augmented', 'Outcast'),
(3, 8000, NULL, 'Second Lieutenant', 'Disciple', 'Node', 'Damned'),
(4, 20000, NULL, 'First Lieutenant', 'Knight', 'Symbiont', 'Prosecutor'),
(5, 40000, NULL, 'Captain', 'Priest', 'Cyborg', 'Persecutor'),
(6, 65000, NULL, 'Major', 'Paladin', 'Freedom Fighter', 'Purger'),
(7, 100000, NULL, 'Colonel', 'Crusader', 'Sentient AI', 'War Demon'),
(8, 200000, 5, 'Major General', 'Evaluator', 'Commander', 'Angel of Wrath'),
(9, 300000, 2, 'General', 'Avatar-of-War', 'Elite Commander', 'Dark Archangel'),
(10, 400000, 1, 'Field Marshal', 'Champion', 'Supreme Commander', 'Seth-Iavow');

--
-- Daten für Tabelle `schema_version`
--

INSERT INTO `schema_version` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
(1, '1', '<< Flyway Baseline >>', 'BASELINE', '<< Flyway Baseline >>', NULL, 'gwapi', '2016-09-10 16:23:46', 0, 1);

--
-- Daten für Tabelle `sun_system`
--

INSERT INTO `sun_system` (`id`, `name`, `x`, `y`, `z`) VALUES
(1, 'center / SERAPH', 0, 0, 0),
(2, 'left / UEF', 1, 0, 0),
(3, 'top / AEON', 0, 1, 0),
(4, 'right / CYB+AEON', -1, 0, 0),
(5, 'bottom / SERAPH+CYB', 0, -1, 0),
(6, 'bottom right / CYB', 1, 1, 0);

--
-- Daten für Tabelle `unit`
--

INSERT INTO `unit` (`id`, `fa_uid`, `name`, `faction`, `tech_level`, `price`) VALUES
(1, 'ual0101', 'Land Scout', 'A', 1, 8),
(2, 'url0101', 'Land Scout', 'C', 1, 8),
(3, 'uel0101', 'Land Scout', 'U', 1, 12),
(4, 'xsl0101', 'Combat Scout', 'S', 1, 20),
(5, 'ual0103', 'Mobile Light Artillery', 'A', 1, 36),
(6, 'url0103', 'Mobile Light Artillery', 'C', 1, 36),
(7, 'uel0103', 'Mobile Light Artillery', 'U', 1, 36),
(8, 'xsl0103', 'Mobile Light Artillery', 'S', 1, 54),
(9, 'ual0104', 'Mobile Anti-Air Gun', 'A', 1, 28),
(10, 'url0104', 'Mobile Anti-Air Gun', 'C', 1, 28),
(11, 'uel0104', 'Mobile Anti-Air Gun', 'U', 1, 28),
(12, 'xsl0104', 'Mobile Anti-Air Gun', 'S', 1, 28);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
