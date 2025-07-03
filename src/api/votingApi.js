import axios from 'axios';

const BASE_URL =  process.env.REACT_APP_API +"vote";

export const doVote = async (vote)=>{
  const response = await axios.post(BASE_URL, vote);
  return response.data;
}
