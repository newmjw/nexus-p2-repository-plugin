/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2007-2012 Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.plugins.p2.repository.internal.tasks;

import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import org.sonatype.nexus.formfields.FormField;
import org.sonatype.nexus.formfields.RepoOrGroupComboFormField;
import org.sonatype.nexus.formfields.StringTextFormField;
import org.sonatype.nexus.tasks.descriptors.AbstractScheduledTaskDescriptor;
import org.sonatype.nexus.tasks.descriptors.ScheduledTaskDescriptor;

@Named
public class P2RepositoryAggregatorTaskDescriptor
    extends AbstractScheduledTaskDescriptor
    implements ScheduledTaskDescriptor
{

    public static final String ID = "P2RepositoryAggregatorTask";

    public static final String REPO_OR_GROUP_FIELD_ID = "repositoryId";

    public static final String RESOURCE_STORE_PATH_FIELD_ID = "resourceStorePath";

    private final RepoOrGroupComboFormField repoField = new RepoOrGroupComboFormField( REPO_OR_GROUP_FIELD_ID,
        FormField.MANDATORY );

    private final StringTextFormField resourceStorePathField = new StringTextFormField( RESOURCE_STORE_PATH_FIELD_ID,
        "Repository path",
        "Enter a repository path to run the task in recursively (ie. \"/\" for root or \"/org/apache\").",
        FormField.OPTIONAL );

    @Override
    public String getId()
    {
        return ID;
    }

    @Override
    public String getName()
    {
        return "Rebuild P2 repository";
    }

    @Override
    public List<FormField> formFields()
    {
        return Arrays.<FormField> asList( repoField, resourceStorePathField );
    }

}
