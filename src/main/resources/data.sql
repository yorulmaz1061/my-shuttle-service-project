INSERT INTO public.address (
    id, insert_date_time, insert_user_id, is_deleted,
    last_update_date_time, last_update_user_id,
    address_line, address_type, city, email, phone_number, street
)
VALUES
    (1, '2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Address line 1', 'Student', 'Denizli', null, '5533321033', 'bagbasi'),
    (2, '2022-01-05 00:00:00', 2, false, '2022-01-05 00:00:00', 2, 'Address line 2', 'Driver', 'Denizli', null, '5533321033', 'adalet'),
    (3, '2022-01-05 00:00:00', 3, false, '2022-01-05 00:00:00', 3, 'Address line 3', 'Hostess', 'Denizli', null, '5533321033', 'gerzele'),
    (4, '2022-01-05 00:00:00', 4, false, '2022-01-05 00:00:00', 4, 'Address line 1', 'Parent', 'Denizli', null, '5533321033', 'yenisafak');
INSERT INTO public.driver (
    id, insert_date_time, insert_user_id,
    is_deleted, last_update_date_time,
    last_update_user_id, driver_license_number,
    driver_name, driver_tc_id, status, adress_id
)
VALUES (2, '2022-01-05 00:00:00',1,false,'2022-01-05 00:00:00',1,'XXX15','hasan kacan','11111111111','Active',1)