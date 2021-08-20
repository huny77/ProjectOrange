import { instance } from './index'

// 메인 피드 인기 탭 API, 랭킹 조회 API
function popularMain(challengeData) {
    return instance.get('/api/challenge/ranking/point', challengeData)
  }
  
// 메인 피드 신규 탭 API
function newMain(challengeData) {
  return instance.get('/api/challenge/ranking/startdate', challengeData)
}
  
// 메인 피드 추천 탭 API
function recommendMain(challengeData) {
  return instance.get('/api/challenge/list/random/8', challengeData)
}

// 챌린지 전체 리스트 API
function listMain(challengeData) {
  return instance.get('/api/challenge/list', challengeData)
}

export { 
  popularMain, 
  newMain, 
  recommendMain,
  listMain,
 }