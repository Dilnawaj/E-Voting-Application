import axios from 'axios';

const BASE_URL =  process.env.REACT_APP_API +"voter";



export const registerVoter = async (voter)=>{
  const response = await axios.post(BASE_URL, voter);
  return response.data;
}

export const getAllVoters = async ()=>{
  const response = await axios.get(BASE_URL);
  return response.data;
}