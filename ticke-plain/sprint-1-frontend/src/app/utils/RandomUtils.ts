export function randomString(length) {
  const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
  let result = '';
  for (let i = length; i > 0; --i) { result += chars[Math.floor(Math.random() * chars.length)]; }
  return result;
}
