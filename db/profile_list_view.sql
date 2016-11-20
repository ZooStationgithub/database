drop view if exists profile_search_view;

create view profile_search_view as
select 
	p.id as id,
    p.zs_number as zs_number,
    (
		select group_concat(rt.name separator '; ')
        from profile_ranks pr
            left join rank_types rt on rt.id = pr.rank_type_id
        where pr.profile_id = p.id
    ) as ranks,
    mpl.name as main_programming_language,
    spl.name as second_programming_language,
    (
		select group_concat(f.name separator '; ')
		from known_frameworks kf
			left join frameworks f on f.id = kf.framework_id
		where kf.profile_id = p.id
    ) as frameworks,
    p.test_rating as rating,
    oc.name as origin_country,
    (
		select group_concat(c.name separator '; ')
        from preferred_countries pc
            left join countries c on c.id = pc.country_id
		where pc.profile_id = p.id
    ) as preferred_countries,
    ct.name as contract_type,
    p.visa_needed as visa_needed,
    (
		select group_concat(c.name separator '; ')
        from preferred_company_types pct
            left join company_types c on c.id = pct.company_type_id
		where pct.profile_id = p.id
    ) as preferred_company_types,
    p.experience as experience,
    (
		select group_concat(r.name separator '; ')
        from profile_roles pr
            left join role_types r on r.id = pr.role_type_id
		where pr.profile_id = p.id
    ) as preferred_roles,
    p.work_history as work_history,
    p.english_level as english_level,
    p.travel_time as travel_time,
    p.preferred_city as preferred_city,
    p.availability as availability,
    p.hours_per_week as hours_per_week,
    p.relocation_reason as relocation_reason
from profiles p
	left join programming_languages mpl on mpl.id = p.main_pr_lng_id
    left join programming_languages spl on spl.id = p.second_pr_lng_id
    left join countries oc on oc.id = p.origin_country_id
    left join contract_types ct on ct.id = p.contract_type_id
;