import axios from 'axios';

const BASE_URL =  process.env.REACT_APP_API +"admin";


export const registerAdmin = async (admin)=>{
  const response = await axios.post(BASE_URL,admin);
  return response.data;
}
