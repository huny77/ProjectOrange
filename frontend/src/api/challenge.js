import { instance } from './index'

// 챌린지를 생성하는 API
function createChallenge(challengeData) {
  return instance.post('/api/challenge/register/new-challenge', challengeData)
}

// 특정 챌린지 1개를 조회하는 API
function challengeDetail(challengeId) {
  return instance.get(`/api/challenge/${challengeId}`)
}

// 뱃지를 조회하는 API
function badgeList(badgeData) {
  return instance.get('/api/badge/list', badgeData)
}

// 유저 챌린지 정보를 불러오는 API
function userChallenge(myData) {
  return instance.get('/api/challenge/search/user', myData)
}

// 댓글을 작성하는 API
function createComment(commentData) {
  return instance.post('/api/comment/create', commentData)
}

// 챌린지 기간을 조회하는 API
function getPeriod(periodData) {
  return instance.get('/api/period/list', periodData)
}

// 챌린지 카테고리를 조회하는 API
function getCategory(categoryData) {
  return instance.get('/api/category/list', categoryData)
}

export { 
  createChallenge,
  badgeList,
  challengeDetail,
  userChallenge,
  createComment,
  getPeriod,
  getCategory,
}