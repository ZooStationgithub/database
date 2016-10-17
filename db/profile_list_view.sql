create or replace view profile_search_view as
select 
	p.id as id,
    p.zs_number as zs_number,
    rk.rank_list as ranks,
    mpl.name as main_programming_language,
    spl.name as second_programming_language,
    f.framework_list as frameworks,
    p.test_rating as rating,
    oc.name as origin_country,
    c.country_list as preferred_countries,
    ct.name as contract_type,
    p.visa_needed as visa_needed,
    cp.company_list as preferred_company_types,
    p.experience as experience,
    r.role_list as preferred_roles,
    p.work_history as work_history,
    p.english_level as english_level,
    p.travel_time as travel_time,
    p.preferred_city as preferred_city,
    p.availability as availability,
    p.hours_per_week as hours_per_week,
    p.relocation_reason as relocation_reason
from profiles p
	left join 
    (
		select p.id as profile_id, group_concat(rt.name separator ', ') as rank_list
        from profiles p
			left join profile_ranks r on r.profile_id = p.id
            left join rank_types rt on rt.id = r.rank_type_id
		group by p.id            
    ) as rk on p.id = rk.profile_id
	left join programming_languages mpl on mpl.id = p.main_pr_lng_id
    left join programming_languages spl on spl.id = p.second_pr_lng_id
	left join 
	(
		select p.id as profile_id, group_concat(f.name separator ', ') as framework_list
		from profiles p
			left join known_frameworks kf on kf.profile_id = p.id
			left join frameworks f on f.id = kf.framework_id
		group by p.id
	) as f on p.id = f.profile_id
    left join countries oc on oc.id = p.origin_country_id
    left join
    (
		select p.id as profile_id, group_concat(c.name separator ', ') as country_list
        from profiles p
			left join preferred_countries pc on pc.profile_id = p.id
            left join countries c on c.id = pc.country_id
		group by p.id
    ) as c on p.id = c.profile_id
    left join contract_types ct on ct.id = p.contract_type_id
    left join
    (
		select p.id as profile_id, group_concat(c.name separator ', ') as company_list
        from profiles p
			left join preferred_company_types pc on pc.profile_id = p.id
            left join company_types c on c.id = pc.company_type_id
		group by p.id
    ) as cp on p.id = cp.profile_id
    left join
    (
		select p.id as profile_id, group_concat(r.name separator ', ') as role_list
        from profiles p 
			left join profile_roles pr on pr.profile_id = p.id
            left join role_types r on r.id = pr.role_type_id
		group by p.id
    ) as r on p.id = r.profile_id
;