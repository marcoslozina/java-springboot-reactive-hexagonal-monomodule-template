import http from 'k6/http';
import { sleep, check } from 'k6';

export let options = {
  vus: 50,
  duration: '30s',
};

export default function () {
  let res = http.get('http://localhost:8080/api/health');
  check(res, { 'status is 200': (r) => r.status === 200 });
  sleep(1);
}
