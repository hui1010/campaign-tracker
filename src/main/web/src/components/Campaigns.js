import React from 'react';
import Campaign from './Campaign';
import Menu from './Menu';


const Campaigns = ({campaigns, matched, onEdit, onDelete, sortByEndDate, sortByPrice, sortByName, sortByStore}) => {
    return (
        <>
            <Menu sortByEndDate={sortByEndDate} sortByPrice={sortByPrice} sortByName={sortByName} sortByStore={sortByStore}/>
            <table className="campaign-table" cellSpacing="0">
                <thead>
                    <tr>
                        <td>Name</td>
                        <td>Store</td>
                        <td>Price</td>
                        <td>Start date</td>
                        <td>End date</td>
                    </tr>
                </thead>
                <tbody>
                {campaigns.map((campaign) => <Campaign key={campaign.id} campaign={campaign} matched={matched} onEdit={onEdit} onDelete={onDelete}/>)}
                </tbody>
            </table>
            
        </>
    )
}

export default Campaigns
