import React, { useEffect, useState } from 'react';
import { getResourcesList } from './Resources.api';
import { Resource } from './types';

// interface ResourcesProps {
//   Resource: any

// }

// const Resources: React.FC<ResourcesProps> = ({Resource}) => {

// }

function Resources() {
  const [resources, setResource] = useState<Resource[] | null>(null)
  useEffect(() => {
    const fetchResourceList = async () => {
      const resourceList = await getResourcesList()
      console.log(resourceList)
      setResource(resourceList)
    }
    fetchResourceList()
  },[])
  return (
    <div className='resources'>
      <h1>Add Resources</h1>
      {JSON.stringify(resources)}
    </div>
  );
}

export default Resources;