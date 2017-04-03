local times = redis.call('incr', KEYS[1]);

if times==1 then
	-- 刚刚创建为其设置生存时间
	redis.call('expire', KEYS[1], ARGV[2]);
end

if times > tonumber(ARGV[1]) then
	return 1;
else
	return 0;
end