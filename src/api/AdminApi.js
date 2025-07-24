import axios from 'axios';

const BASE_URL =  process.env.REACT_APP_API+"election-commission";


export const registerAdmin = async (admin)=>{
  const response = await axios.post(BASE_URL,admin);
  return response.data;
}

export const getAdminByEmail = async (email)=>{
  const response = await axios.get(`${BASE_URL}?email=${email}`);
  return response.data;
}
