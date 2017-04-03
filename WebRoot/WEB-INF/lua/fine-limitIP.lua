local times = redis.call('llen', KEYS[1]);
local first = redis.call('lindex', KEYS[1], '0');

if times < tonumber(ARGV[2]) then
	-- 没超过指定长度，将时间戳放进队列中
	redis.call('rpush', KEYS[1], ARGV[1]);
	return 1;
else then
	-- 超过指定长度，通过时间差判断是否允许访问
	if math.abs(tonumber(ARGV[1])-first) < ARGV[2] then
		return 0;
	else then
		redis.call('rpush', KEYS[1], ARGV[1]);
		return 1;
	end
end