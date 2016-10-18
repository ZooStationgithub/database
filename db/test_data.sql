START TRANSACTION;

DELETE FROM `known_frameworks`;
DELETE FROM `preferred_countries`;
DELETE FROM `preferred_company_types`;
DELETE FROM `profile_ranks`;
DELETE FROM `profile_roles`;
DELETE FROM `profiles`;
DELETE FROM `accounts`;
DELETE FROM `frameworks`;
DELETE FROM `programming_languages`;

-- Add accounts
INSERT INTO `accounts` (`id`, `login`, `password`, `creation_date`, `activation_date`, `group_id`)
VALUES
	(1, 'su', '$2a$10$soHnPG7QLJ.ge.XW2ADcIe7zSNBPMinITtUsIQs77rZRWO3hV6UgG', subdate(current_timestamp(), 10), current_timestamp(), 1)
;

-- Add programming languages
DELETE FROM `programming_languages`;
INSERT INTO `programming_languages` (`id`, `name`)
VALUES
	(1, 'Java'),
    (2, 'C++'),
    (3, 'C#'),
    (4, 'JavaScript')
;

-- Add frameworks
INSERT INTO `frameworks` (`id`, `name`, `programming_language_id`)
VALUES
	(1, 'Spring', 1),
    (2, 'Hibernate', 1),
    (3, 'AngularJS', 4),
    (4, '.NET', 3),
    (5, 'NodeJS', 4)
;

-- Add profile
INSERT INTO `profiles` (`id`, `zs_number`, `main_pr_lng_id`, `second_pr_lng_id`, `test_rating`, `origin_country_id`, `visa_needed`, `experience`, `work_history`, `english_level`, 
						`travel_time`, `preferred_city`, `availability`, `hours_per_week`, `relocation_reason`, `contract_type_id`)
VALUES
	(1, '4567890', 1, 4, 40, 100, 0, 3, 'trakaalalalal', 3, 40, 'Amsterdam', 3, 40, 'frjfoprffrf frf', 1),
    (2, '4567891', 4, 1, 40, 100, 0, 3, 'trakaalalalal', 3, 40, 'Amsterdam', 3, 40, 'frjfoprffrf frf', 1),
    (3, '4567892', 3, null, 40, 100, 0, 3, 'trakaalalalal', 3, 40, 'Amsterdam', 3, 40, 'frjfoprffrf frf', 1)
;
INSERT INTO `known_frameworks` (`profile_id`, `framework_id`)
VALUES
	(1, 2), (1, 1),
    (2, 3)
;
INSERT INTO `preferred_countries` (`profile_id`, `country_id`)
VALUES
	(1, 100), (1, 101), (1, 102),
    (2, 100),
    (3, 105), (3, 106)
;
INSERT INTO `preferred_company_types` (`profile_id`, `company_type_id`)
VALUES
	(1, 1),
    (2, 1),
    (3, 1), (3, 2)
;
INSERT INTO `profile_ranks` (`profile_id`, `rank_type_id`)
VALUES
	(1, 1),
    (2, 2), 
    (3, 3)
;
INSERT INTO `profile_roles` (`profile_id`, `role_type_id`)
VALUES
	(1, 1),
    (2, 1),
    (3, 2)
;

COMMIT;