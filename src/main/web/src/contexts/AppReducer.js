export default (state, action) => {
    switch (action.type) {
        case 'DELETE_CAMPAIGN' : return {
            ...state, 
            campaigns: state.campaigns.filter(campaign => campaign.id !== action.payload)
        }
        case 'ADD_CAMPAIGN' : return {
            ...state, 
            campaigns: [action.payload, ...campaigns]
        }
        default: return state;
    }
}