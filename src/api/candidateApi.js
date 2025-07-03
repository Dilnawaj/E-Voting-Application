import axios from 'axios';

const BASE_URL =  process.env.REACT_APP_API +"candidate";

export const getAllCandidates =  async ()=>{
  const response = await axios.get(BASE_URL);
  return response.data;
}

export const registerCandidate = async (candidate)=>{
  const response = await axios.post(BASE_URL,candidate);
  return response.data;
}