import axios from 'axios';

const BASE_URL =  process.env.REACT_APP_API +"auth/login";


export const login = async (login)=>{
  const response = await axios.post(BASE_URL,login);
  return response.data;
}
